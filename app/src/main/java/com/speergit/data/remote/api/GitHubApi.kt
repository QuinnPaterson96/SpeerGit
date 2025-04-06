package com.speergit.data.remote.api

import com.speergit.data.remote.dto.GitHubUserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): GitHubUserDto
}
