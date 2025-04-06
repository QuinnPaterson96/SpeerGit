package com.speergit.data.remote.dto

import com.speergit.domain.model.GitHubUser

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitHubUserDto(
    val login: String,
    val name: String?,
    val bio: String?,
    @Json(name = "avatar_url") val avatarUrl: String,
    val followers: Int,
    val following: Int
) {
    fun toDomain(): GitHubUser = GitHubUser(
        login = login,
        name = name.orEmpty(),
        bio = bio.orEmpty(),
        avatarUrl = avatarUrl,
        followers = followers,
        following = following
    )
}
