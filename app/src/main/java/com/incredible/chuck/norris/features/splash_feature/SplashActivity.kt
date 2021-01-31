package com.incredible.chuck.norris.features.splash_feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.incredible.chuck.norris.navigation.Screens
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (viewModel.checkOnboarding()) {
            startMainActivity()
        } else {
            startOnboardingActivity()
        }
    }

    private fun startMainActivity() {
        startActivity(Screens.MainScreen().getActivityIntent(this))
        finish()
    }

    private fun startOnboardingActivity() {
        startActivity(Screens.OnboardingScreen().getActivityIntent(this))
        finish()
    }
}