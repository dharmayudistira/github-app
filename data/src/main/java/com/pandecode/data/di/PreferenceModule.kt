package com.pandecode.data.di

import androidx.datastore.preferences.preferencesDataStore
import com.pandecode.data.preference.SettingPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferenceModule = module {
    single {
        preferencesDataStore(name = "settings").getValue(
            androidContext(),
            SettingPreferences::javaClass
        )
    }

    single {
        SettingPreferences(get())
    }
}