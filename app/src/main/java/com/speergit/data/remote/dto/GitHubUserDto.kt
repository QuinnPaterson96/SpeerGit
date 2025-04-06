package com.speergit.data.remote.dto

import com.speergit.domain.model.GitHubUser

data class GitHubUserDto(
    val login: String,
    val name: String?,
    val bio: String?,
    val avatar_url: String,
    val followers: Int,
    val following: Int
) {
    fun toDomain(): GitHubUser = GitHubUser(
        login = login,
        name = name.orEmpty(),
        bio = bio.orEmpty(),
        avatarUrl = avatar_url,
        followers = followers,
        following = following
    )
}
