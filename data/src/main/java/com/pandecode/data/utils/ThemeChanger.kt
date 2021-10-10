package com.pandecode.data.utils

import androidx.appcompat.app.AppCompatDelegate

object ThemeChanger {
    fun updateTheme(mode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(mode)
        return true
    }
}