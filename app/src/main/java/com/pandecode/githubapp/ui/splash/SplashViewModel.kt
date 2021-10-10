package com.pandecode.githubapp.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandecode.data.preference.SettingPreferences
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel(private val pref: SettingPreferences) : ViewModel() {
    private val _readyToIntent = MutableLiveData<Boolean>()
    val readyToIntent: LiveData<Boolean> get() = _readyToIntent

    private val _theme = MutableLiveData<Int>()
    val theme: LiveData<Int> = _theme

    fun getThemeMode() {
        _readyToIntent.value = false

        viewModelScope.launch {
            pref.getThemeSetting().collect {
                _theme.postValue(it)

                _readyToIntent.postValue(true)
            }
        }
    }
}