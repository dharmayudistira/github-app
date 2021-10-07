package com.pandecode.githubapp.favorite

import com.pandecode.githubapp.favorite.ui.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}