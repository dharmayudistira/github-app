package com.pandecode.githubapp.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandecode.data.preference.SettingPreferences
import com.pandecode.data.utils.ThemeChanger
import kotlinx.coroutines.launch

class SettingsViewModel(private val pref: SettingPreferences) : ViewModel() {

    fun updateTheme(mode: Int) {
        ThemeChanger.updateTheme(mode)

        viewModelScope.launch {
            pref.saveThemeSetting(mode)
        }
    }

}