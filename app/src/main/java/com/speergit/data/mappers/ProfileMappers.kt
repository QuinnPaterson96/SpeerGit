package com.speergit.data.mappers

import com.speergit.data.local.entities.CachedGitHubUserEntity
import com.speergit.domain.model.GitHubUser

fun GitHubUser.toEntity(): CachedGitHubUserEntity = CachedGitHubUserEntity(
    login = login,
    name = name,
    bio = bio,
    avatarUrl = avatarUrl,
    followers = followers,
    following = following,
    cachedAt = System.currentTimeMillis()
)

fun CachedGitHubUserEntity.toDomain(): GitHubUser = GitHubUser(
    login = login,
    name = name,
    bio = bio,
    avatarUrl = avatarUrl,
    followers = followers,
    following = following
)
