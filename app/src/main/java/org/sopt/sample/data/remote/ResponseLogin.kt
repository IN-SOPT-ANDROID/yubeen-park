package org.sopt.sample.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLogin(
    @SerialName("status")
    val status:Int,
    @SerialName("message")
    val message:String
)
