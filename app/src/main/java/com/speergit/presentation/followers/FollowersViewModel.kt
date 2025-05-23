package com.speergit.presentation.followers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.speergit.domain.model.GitHubUser
import com.speergit.domain.model.GitHubUserPreview
import com.speergit.domain.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FollowersUiState>(FollowersUiState.Loading)
    val uiState: StateFlow<FollowersUiState> = _uiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    fun loadFollowers(username: String, isRefresh: Boolean = false) {
        if (isRefresh) {
            _isRefreshing.value = true
        } else {
            _uiState.value = FollowersUiState.Loading
        }

        viewModelScope.launch {
            try {
                val followers = repository.getFollowers(username)
                _uiState.value = FollowersUiState.Success(followers)
            } catch (e: Exception) {
                _uiState.value = FollowersUiState.Error("Couldn't load followers")
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}

sealed class FollowersUiState {
    object Loading : FollowersUiState()
    data class Success(val followers: List<GitHubUserPreview>) : FollowersUiState()
    data class Error(val message: String) : FollowersUiState()
}
