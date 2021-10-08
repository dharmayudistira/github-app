package com.pandecode.githubapp.settings.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.pandecode.githubapp.settings.R
import com.pandecode.githubapp.settings.di.settingsModule
import com.pandecode.githubapp.settings.utils.ThemeMode
import org.koin.android.viewmodel.ext.android.viewModel
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTheme().observe(requireActivity(), {
            updateTheme(it)
        })

    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val prefTheme = findPreference<ListPreference>(getString(R.string.pref_key_theme))
        prefTheme?.setOnPreferenceChangeListener { _, newValue ->
            when(newValue) {
                getString(R.string.pref_theme_auto) -> {
                    val mode = ThemeMode.AUTOMATIC.value
                    updateTheme(mode)
                    viewModel.updateTheme(mode)
                }
                getString(R.string.pref_theme_dark) -> {
                    val mode = ThemeMode.DARK.value
                    updateTheme(mode)
                    viewModel.updateTheme(mode)
                }
                getString(R.string.pref_theme_light) -> {
                    val mode = ThemeMode.LIGHT.value
                    updateTheme(mode)
                    viewModel.updateTheme(mode)
                }
                else -> {
                    val mode = ThemeMode.AUTOMATIC.value
                    updateTheme(mode)
                    viewModel.updateTheme(mode)
                }
            }

            true
        }

    }

    private fun updateTheme(mode: Int) : Boolean {
        AppCompatDelegate.setDefaultNightMode(mode)
        return true
    }
}