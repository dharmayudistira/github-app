package com.pandecode.githubapp.settings.utils

import androidx.appcompat.app.AppCompatDelegate

enum class ThemeMode(val value: Int) {

    AUTOMATIC(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    DARK(AppCompatDelegate.MODE_NIGHT_YES),
    LIGHT(AppCompatDelegate.MODE_NIGHT_NO)

}