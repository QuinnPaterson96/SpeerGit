package com.speergit.domain.model

data class GitHubUser(
    val login: String,
    val name: String,
    val bio: String,
    val avatarUrl: String,
    val followers: Int,
    val following: Int
)
