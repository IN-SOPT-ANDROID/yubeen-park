package org.sopt.sample.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface UserListService {
    @GET("api/users?page=2")
    fun getUserList(): Call<ResponseUserList>
}