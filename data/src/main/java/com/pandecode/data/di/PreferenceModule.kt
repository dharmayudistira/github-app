package com.pandecode.data.di

import androidx.datastore.preferences.preferencesDataStore
import com.pandecode.data.preference.SettingPreferences
import com.pandecode.data.utils.PreferenceConstants.DATASTORE_PREF_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferenceModule = module {
    single {
        preferencesDataStore(name = DATASTORE_PREF_NAME).getValue(
            androidContext(),
            SettingPreferences::javaClass
        )
    }

    single {
        SettingPreferences(get())
    }
}