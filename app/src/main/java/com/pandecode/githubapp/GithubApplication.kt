package com.pandecode.githubapp

import android.app.Application
import com.pandecode.data.di.networkModule
import com.pandecode.data.di.repositoryModule
import com.pandecode.githubapp.di.useCaseModule
import com.pandecode.githubapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GithubApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }

    }
}