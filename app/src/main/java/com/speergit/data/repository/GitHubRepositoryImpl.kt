package com.speergit.data.repository

import com.speergit.data.remote.api.GitHubApi
import com.speergit.domain.model.GitHubUser
import com.speergit.domain.repository.GitHubRepository
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    private val api: GitHubApi
) : GitHubRepository {
    override suspend fun getUser(username: String): GitHubUser {
        return api.getUser(username).toDomain()
    }
}
