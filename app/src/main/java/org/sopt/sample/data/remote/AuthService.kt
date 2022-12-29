package org.sopt.sample.data.remote

import okhttp3.MultipartBody
import org.sopt.sample.data.remote.request.RequestLogin
import org.sopt.sample.data.remote.request.RequestSignup
import org.sopt.sample.data.remote.response.ResponseLogin
import org.sopt.sample.data.remote.response.ResponseSignup
import retrofit2.Call
import retrofit2.http.*

interface AuthService {

    @POST("api/user/signup")
    fun signup(
        @Body request: RequestSignup
    ): Call<ResponseSignup>

    @POST("api/user/signin")
    suspend fun login(
        @Body request: RequestLogin
    ): ResponseLogin

    @Multipart
    @POST("api/user/{userId}/image")
    fun uploadProfileImage(
        @Path("userId") userId: Int,
        @Part("image") image: MultipartBody.Part
    ): Call<Unit>
}