package com.speergit.di.modules

import android.provider.ContactsContract.Profile
import com.speergit.data.local.dao.ProfileDao
import com.speergit.data.remote.api.GitHubApi
import com.speergit.data.repository.GitHubRepositoryImpl
import com.speergit.domain.repository.GitHubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }


    @Provides
    @Singleton
    fun provideGitHubApi(okHttpClient: OkHttpClient, baseUrl: String): GitHubApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }


    @Provides
    @Singleton
    fun provideRepository(api: GitHubApi, dao: ProfileDao): GitHubRepository {
        return GitHubRepositoryImpl(api = api, dao = dao)
    }
}
