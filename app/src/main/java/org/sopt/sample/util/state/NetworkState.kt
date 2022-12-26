package org.sopt.sample.util.state

sealed class NetworkState {
    object Success : NetworkState()
    object Failure : NetworkState()
    data class Error(val e: Throwable) : NetworkState()
}
