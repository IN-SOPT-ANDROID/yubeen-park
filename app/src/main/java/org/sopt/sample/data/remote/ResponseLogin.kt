package org.sopt.sample.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.sample.data.UserInfo

@Serializable
data class ResponseLogin(
    val status: Int,
    val message: String,
    @SerialName("result")
    val user: UserInfo
)
