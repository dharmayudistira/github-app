package com.pandecode.data.di

import com.pandecode.data.domain.repository.IGithubRepository
import com.pandecode.data.source.GithubRepository
import com.pandecode.data.source.local.LocalDataSource
import com.pandecode.data.source.remote.RemoteDataSource
import org.koin.dsl.module

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    single<IGithubRepository> { GithubRepository(get(), get()) }
}