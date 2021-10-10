package com.pandecode.githubapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.pandecode.data.utils.ThemeChanger.updateTheme
import com.pandecode.githubapp.R
import com.pandecode.githubapp.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


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
            if (it) {
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
}