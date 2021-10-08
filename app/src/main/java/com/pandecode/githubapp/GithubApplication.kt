package com.pandecode.githubapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.CoilUtils
import com.pandecode.data.di.*
import com.pandecode.githubapp.di.useCaseModule
import com.pandecode.githubapp.di.viewModelModule
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubApplication : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GithubApplication)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    preferenceModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }

    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this@GithubApplication)
            .crossfade(true)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(this@GithubApplication))
                    .build()
            }
            .build()
    }
}