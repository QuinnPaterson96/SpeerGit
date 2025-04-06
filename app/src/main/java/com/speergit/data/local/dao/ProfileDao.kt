package com.speergit.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.speergit.data.local.entities.CachedGitHubUserEntity

@Dao
interface ProfileDao {
    @Query("SELECT * FROM cached_profiles WHERE login = :username LIMIT 1")
    suspend fun getCachedProfile(username: String): CachedGitHubUserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProfile(profile: CachedGitHubUserEntity)

    @Query("DELETE FROM cached_profiles WHERE login = :username")
    suspend fun deleteProfile(username: String)
}
