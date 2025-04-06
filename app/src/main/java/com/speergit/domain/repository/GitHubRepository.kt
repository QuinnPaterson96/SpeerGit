package com.speergit.domain.repository

import com.speergit.domain.model.GitHubUser
import com.speergit.domain.model.GitHubUserPreview

interface GitHubRepository {
    suspend fun getUser(username: String): GitHubUser
    suspend fun getFollowers(username: String): List<GitHubUserPreview>
    suspend fun getFollowing(username: String): List<GitHubUserPreview>

}
