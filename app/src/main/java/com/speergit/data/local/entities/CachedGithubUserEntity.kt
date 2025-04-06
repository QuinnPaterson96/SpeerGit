package com.speergit.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_profiles")
data class CachedGitHubUserEntity(
    @PrimaryKey val login: String,
    val name: String,
    val bio: String,
    val avatarUrl: String,
    val followers: Int,
    val following: Int,
    val cachedAt: Long
)
