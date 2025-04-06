package com.speergit.data.remote.api

import com.speergit.data.remote.dto.GitHubUserDto
import com.speergit.data.remote.dto.GitHubUserPreviewDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): GitHubUserDto

    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): List<GitHubUserPreviewDto>

    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): List<GitHubUserPreviewDto>

}
