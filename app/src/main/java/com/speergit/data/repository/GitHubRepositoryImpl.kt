package com.speergit.data.repository

import com.speergit.data.local.dao.ProfileDao
import com.speergit.data.mappers.toDomain
import com.speergit.data.mappers.toEntity
import com.speergit.data.remote.api.GitHubApi
import com.speergit.domain.model.GitHubUser
import com.speergit.domain.model.GitHubUserPreview
import com.speergit.domain.repository.GitHubRepository
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    private val api: GitHubApi,
    private val dao: ProfileDao
) : GitHubRepository {
    override suspend fun getUser(username: String): GitHubUser {
        val cached = dao.getCachedProfile(username)
        val now = System.currentTimeMillis()

        if (cached != null && now - cached.cachedAt < 60 * 60 * 1000) {
            return cached.toDomain()
        }

        val user = api.getUser(username).toDomain()
        dao.upsertProfile(user.toEntity())
        return user
    }

    override suspend fun fetchAndCacheUser(username: String): GitHubUser {
        val user = api.getUser(username).toDomain()
        dao.upsertProfile(user.toEntity())
        return user
    }

    override suspend fun invalidateUserCache(username: String) {
        dao.deleteProfile(username)
    }

    override suspend fun getFollowers(username: String): List<GitHubUserPreview> {
        return api.getFollowers(username).map { it.toDomain() }
    }

    override suspend fun getFollowing(username: String): List<GitHubUserPreview> {
        return api.getFollowing(username = username).map { it.toDomain() }
    }

}
