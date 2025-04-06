package com.speergit.data.repository

import com.speergit.data.remote.api.GitHubApi
import com.speergit.domain.model.GitHubUser
import com.speergit.domain.model.GitHubUserPreview
import com.speergit.domain.repository.GitHubRepository
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    private val api: GitHubApi
) : GitHubRepository {
    override suspend fun getUser(username: String): GitHubUser {
        return api.getUser(username).toDomain()
    }
    override suspend fun getFollowers(username: String): List<GitHubUserPreview> {
        return api.getFollowers(username).map { it.toDomain() }
    }

    override suspend fun getFollowing(username: String): List<GitHubUserPreview> {
        return api.getFollowing(username = username).map { it.toDomain() }
    }

}
