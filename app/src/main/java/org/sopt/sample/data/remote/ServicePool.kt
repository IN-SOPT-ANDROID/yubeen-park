package org.sopt.sample.data.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServicePool {
    @POST("api/user/signup")
    fun signupService(
        @Body request: RequestSignup
    ): Call<ResponseSignup>
}