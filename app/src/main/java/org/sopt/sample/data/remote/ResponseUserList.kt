package org.sopt.sample.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserList(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<userListInfo>,
    val support: supportInfo
) {
    @Serializable
    data class userListInfo(
        val id: Int,
        val email: String,
        val first_name: String,
        val last_name: String,
        val avatar: String
    )

    @Serializable
    data class supportInfo(
        val url: String,
        val text: String
    )
}
