package com.speergit.presentation.search

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
class SearchViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun searchUser(username: String) {
        viewModelScope.launch {
            _uiState.value = SearchUiState.Loading
            try {
                val user = repository.getUser(username)
                _uiState.value = SearchUiState.Success(user)
            } catch (e: Exception) {
                _uiState.value = SearchUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}

sealed class SearchUiState {
    object Idle : SearchUiState()
    object Loading : SearchUiState()
    data class Success(val user: GitHubUser) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}
