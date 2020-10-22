package com.incredible.chuck.norris.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.incredible.chuck.norris.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkOnboarding()
    }

    private fun checkOnboarding() {
        val sharedPreferences = getSharedPreferences(getString(R.string.onboarding_preference_key), MODE_PRIVATE)
        val isOnboardingScreenShown = sharedPreferences.getBoolean(getString(R.string.onboarding_flag_key), false)

        if (isOnboardingScreenShown) {
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