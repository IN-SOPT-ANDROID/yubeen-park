package org.sopt.sample.repositories

import org.sopt.sample.data.remote.request.RequestLogin
import org.sopt.sample.data.remote.response.ResponseLogin

interface AuthRepository {
    suspend fun login(request: RequestLogin): Result<ResponseLogin>
}