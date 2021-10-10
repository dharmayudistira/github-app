package com.pandecode.githubapp.di

import com.pandecode.data.domain.usecase.GithubInteractor
import com.pandecode.data.domain.usecase.GithubUseCase
import com.pandecode.githubapp.ui.detail.DetailViewModel
import com.pandecode.githubapp.ui.detail.fragment.follower.FollowerViewModel
import com.pandecode.githubapp.ui.detail.fragment.following.FollowingViewModel
import com.pandecode.githubapp.ui.detail.fragment.repository.RepositoryViewModel
import com.pandecode.githubapp.ui.home.HomeViewModel
import com.pandecode.githubapp.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GithubUseCase> { GithubInteractor(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FollowingViewModel(get()) }
    viewModel { FollowerViewModel(get()) }
    viewModel { RepositoryViewModel(get()) }
}