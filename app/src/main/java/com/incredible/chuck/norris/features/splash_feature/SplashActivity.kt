package com.incredible.chuck.norris.features.splash_feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.incredible.chuck.norris.dagger.factory.ViewModelProviderFactory
import com.incredible.chuck.norris.features.main.MainViewModel
import com.incredible.chuck.norris.navigation.Screens
import dagger.android.support.DaggerAppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(SplashViewModel::class.java)
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