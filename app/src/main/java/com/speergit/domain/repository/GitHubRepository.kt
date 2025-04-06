package com.speergit.domain.repository

import com.speergit.domain.model.GitHubUser

interface GitHubRepository {
    suspend fun getUser(username: String): GitHubUser
}
