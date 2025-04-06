package com.speergit.data.remote.dto

import com.speergit.domain.model.GitHubUserPreview
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitHubUserPreviewDto(
    val login: String,
    @Json(name = "avatar_url") val avatarUrl: String,
) {
    fun toDomain(): GitHubUserPreview = GitHubUserPreview(
        login = login,
        avatarUrl = avatarUrl
    )
}
