package com.speergit.presentation.following

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.speergit.domain.model.GitHubUserPreview
import com.speergit.domain.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FollowingUiState>(FollowingUiState.Loading)
    val uiState: StateFlow<FollowingUiState> = _uiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    fun loadFollowing(username: String, isRefresh: Boolean = false) {
        if (isRefresh) {
            _isRefreshing.value = true
        } else {
            _uiState.value = FollowingUiState.Loading
        }

        viewModelScope.launch {
            try {
                val following = repository.getFollowing(username)
                _uiState.value = FollowingUiState.Success(following)
            } catch (e: Exception) {
                _uiState.value = FollowingUiState.Error("Couldn't load following list")
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}

sealed class FollowingUiState {
    object Loading : FollowingUiState()
    data class Success(val following: List<GitHubUserPreview>) : FollowingUiState()
    data class Error(val message: String) : FollowingUiState()
}

