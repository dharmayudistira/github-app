package com.pandecode.githubapp.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pandecode.data.preference.SettingPreferences
import kotlinx.coroutines.launch

class SettingsViewModel(private val pref: SettingPreferences) : ViewModel() {

    fun updateTheme(mode: Int) {
        viewModelScope.launch {
            pref.saveThemeSetting(mode)
        }
    }

    fun getTheme() = pref.getThemeSetting().asLiveData()

}