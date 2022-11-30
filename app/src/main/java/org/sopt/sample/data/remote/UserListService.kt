package org.sopt.sample.data.remote

import org.sopt.sample.data.remote.response.ResponseUserList
import retrofit2.Call
import retrofit2.http.GET

interface UserListService {
    @GET("api/users?page=2")
    fun getUserList(): Call<ResponseUserList>
}