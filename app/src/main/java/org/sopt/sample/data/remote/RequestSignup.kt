package org.sopt.sample.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignup(
    val email: String,
    @SerialName("password")
    val pw: String,
    val name: String
)
