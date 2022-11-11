package org.sopt.sample.data.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/user/signup")
    fun signup(
        @Body request: RequestSignup
    ): Call<ResponseSignup>
}