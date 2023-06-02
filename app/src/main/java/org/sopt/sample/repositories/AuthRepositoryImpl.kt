package org.sopt.sample.repositories

import org.sopt.sample.data.remote.AuthService
import org.sopt.sample.data.remote.request.RequestLogin
import org.sopt.sample.data.remote.response.ResponseLogin
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun login(request: RequestLogin): Result<ResponseLogin> =
        kotlin.runCatching {
            authService.login(request)
        }

}