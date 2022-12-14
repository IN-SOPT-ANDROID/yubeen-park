package org.sopt.sample.data.remote.response

import kotlinx.serialization.Serializable
import org.sopt.sample.data.UserInfo

@Serializable
data class ResponseSignup(
    val status: Int,
    val message: String,
    val newUser: UserInfo
)
