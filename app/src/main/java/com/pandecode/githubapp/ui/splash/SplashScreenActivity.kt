package com.pandecode.githubapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.pandecode.githubapp.R
import com.pandecode.githubapp.ui.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel


class SplashScreenActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        viewModel.getThemeMode()

        viewModel.theme.observe(this, {
            updateTheme(it)
        })

        viewModel.readyToIntent.observe(this, {
            if(it) {
                navigateToMain()
            }
        })

    }

    private fun navigateToMain() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }

    private fun updateTheme(mode: Int) : Boolean {
        AppCompatDelegate.setDefaultNightMode(mode)
        return true
    }
}