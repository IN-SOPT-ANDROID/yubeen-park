package org.sopt.sample.util.state

sealed class UiState<out T> {
    object Init: UiState<Nothing>()
    object Empty : UiState<Nothing>()
    data class Loading(val isLoading: Boolean) : UiState<Nothing>()
    data class Success<out T>(val items: T) : UiState<T>()
    data class Error(val error: String) : UiState<Nothing>()
}