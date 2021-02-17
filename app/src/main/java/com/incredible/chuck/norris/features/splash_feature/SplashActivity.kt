package com.incredible.chuck.norris.features.splash_feature

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.incredible.chuck.norris.navigation.Screens
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(SplashViewModel::class.java)
    }

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