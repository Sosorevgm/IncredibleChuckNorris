package com.incredible.chuck.norris.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.incredible.chuck.norris.view_model.SplashViewModel
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
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    private fun startOnboardingActivity() {
        val onboardingIntent = Intent(this, OnboardingActivity::class.java)
        startActivity(onboardingIntent)
        finish()
    }
}