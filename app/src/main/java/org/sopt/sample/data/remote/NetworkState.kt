package org.sopt.sample.data.remote

sealed class NetworkState {
    object Success : NetworkState()
    object Failure : NetworkState()
    class Error(val e: Throwable) : NetworkState()
}
