package org.sopt.sample.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignup(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val pw: String,
    @SerialName("name")
    val name: String
)
