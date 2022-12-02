package org.sopt.sample.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.sample.BuildConfig.LOGIN_BASE_URL
import org.sopt.sample.BuildConfig.USERLIST_BASE_URL
import retrofit2.Retrofit

object ApiFactory {
    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply{
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(LOGIN_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
    }

    val retrofitList: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(USERLIST_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
    inline fun <reified T> createList(): T = retrofitList.create<T>(T::class.java)
}

object ServicePool {
    val authService = ApiFactory.create<AuthService>()
    val userListService = ApiFactory.createList<UserListService>()
}