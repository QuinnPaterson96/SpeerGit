package com.speergit.di.modules

import com.speergit.data.remote.api.GitHubApi
import com.speergit.data.repository.GitHubRepositoryImpl
import com.speergit.domain.repository.GitHubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideGitHubApi(baseUrl: String): GitHubApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: GitHubApi): GitHubRepository {
        return GitHubRepositoryImpl(api)
    }
}
