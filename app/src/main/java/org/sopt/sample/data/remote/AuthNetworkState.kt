package org.sopt.sample.data.remote

sealed class AuthNetworkState {
    object Success : AuthNetworkState()
    object Failure : AuthNetworkState()
    class Error(val e: Throwable) : AuthNetworkState()
}
