package com.pandecode.githubapp.di

import com.pandecode.data.domain.usecase.GithubInteractor
import com.pandecode.data.domain.usecase.GithubUseCase
import com.pandecode.githubapp.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GithubUseCase> { GithubInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}