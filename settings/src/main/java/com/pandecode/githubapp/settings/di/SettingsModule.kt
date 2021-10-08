package com.pandecode.githubapp.settings.di

import com.pandecode.githubapp.settings.ui.SettingsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    viewModel { SettingsViewModel(get()) }
}