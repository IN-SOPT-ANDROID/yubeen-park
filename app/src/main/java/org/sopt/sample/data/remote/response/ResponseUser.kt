package org.sopt.sample.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUser(
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    val data: List<UserListInfo>,
    val support: SupportInfo
) {
    @Serializable
    data class UserListInfo(
        val id: Int,
        val email: String,
        @SerialName("first_name")
        val firstName: String,
        @SerialName("last_name")
        val lastName: String,
        val avatar: String
    )

    @Serializable
    data class SupportInfo(
        val url: String,
        val text: String
    )
}
