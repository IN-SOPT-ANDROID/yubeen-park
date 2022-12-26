package org.sopt.sample.util.state

sealed class UiState<out T> {
    object Empty : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<out T>(val items: T) : UiState<T>()
    data class Error(val error: String) : UiState<Nothing>()
}