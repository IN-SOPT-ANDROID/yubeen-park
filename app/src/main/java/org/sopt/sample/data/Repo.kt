package org.sopt.sample.data

import androidx.annotation.DrawableRes

data class Repo(
    @DrawableRes val image: Int,
    val name: String,
    val author: String,
    val type: Int
) {
    companion object {
        const val TEXT_TYPE = 0
        const val REPO_TYPE = 1
    }
}
