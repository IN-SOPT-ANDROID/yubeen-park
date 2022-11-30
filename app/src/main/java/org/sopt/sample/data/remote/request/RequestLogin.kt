package org.sopt.sample.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLogin(
    val email: String,
    @SerialName("password")
    val pw: String
)
