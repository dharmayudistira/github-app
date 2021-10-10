package com.pandecode.githubapp.settings.ui

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.pandecode.githubapp.settings.R
import com.pandecode.githubapp.settings.di.settingsModule
import com.pandecode.githubapp.settings.utils.ThemeMode
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class SettingsFragment : PreferenceFragmentCompat() {

    private val viewModel: SettingsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(settingsModule)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(settingsModule)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val prefTheme = findPreference<ListPreference>(getString(R.string.pref_key_theme))
        prefTheme?.setOnPreferenceChangeListener { _, newValue ->
            when (newValue) {
                getString(R.string.pref_theme_auto) -> {
                    viewModel.updateTheme(ThemeMode.AUTOMATIC.value)
                }
                getString(R.string.pref_theme_dark) -> {
                    viewModel.updateTheme(ThemeMode.DARK.value)
                }
                getString(R.string.pref_theme_light) -> {
                    viewModel.updateTheme(ThemeMode.LIGHT.value)
                }
                else -> {
                    viewModel.updateTheme(ThemeMode.AUTOMATIC.value)
                }
            }

            true
        }

    }
}