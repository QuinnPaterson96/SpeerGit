package com.speergit.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.speergit.data.local.dao.ProfileDao
import com.speergit.data.local.entities.CachedGitHubUserEntity

@Database(
    entities = [CachedGitHubUserEntity::class], // add any other entities here
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}
