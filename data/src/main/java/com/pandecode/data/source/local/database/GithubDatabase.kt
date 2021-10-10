package com.pandecode.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [UserEntity::class],
    exportSchema = false
)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun githubDao(): GithubDao
}