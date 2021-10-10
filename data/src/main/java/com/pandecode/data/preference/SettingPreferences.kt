package com.pandecode.data.preference

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.pandecode.data.utils.PreferenceConstants.THEME_PREF_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingPreferences(private val dataStore: DataStore<Preferences>) {

    private val themeKey = intPreferencesKey(THEME_PREF_KEY)

    fun getThemeSetting(): Flow<Int> {
        return dataStore.data.map { pref ->
            pref[themeKey] ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
    }

    suspend fun saveThemeSetting(mode: Int) {
        dataStore.edit { preferences ->
            preferences[themeKey] = mode
        }
    }
}