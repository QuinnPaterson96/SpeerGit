package com.speergit.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.speergit.domain.model.GitHubUser
import com.speergit.domain.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Idle)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    fun loadProfile(username: String, isRefresh: Boolean = false) {
        if (isRefresh) {
            _isRefreshing.value = true
        } else {
            _uiState.value = ProfileUiState.Loading
        }

        viewModelScope.launch {
            try {
                val user = if (isRefresh) {
                    repository.fetchAndCacheUser(username)
                } else {
                    repository.getUser(username)
                }

                _uiState.value = ProfileUiState.Success(user)
            } catch (e: Exception) {
                _uiState.value = ProfileUiState.Error(e.localizedMessage ?: "Error")
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}


sealed class ProfileUiState {
    object Idle : ProfileUiState()
    object Loading : ProfileUiState()
    data class Success(val user: GitHubUser) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
}
