package com.pandecode.data.di

import androidx.room.Room
import com.pandecode.data.source.local.database.GithubDatabase
import com.pandecode.data.utils.DatabaseConstants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<GithubDatabase>().githubDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GithubDatabase::class.java,
            DatabaseConstants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}