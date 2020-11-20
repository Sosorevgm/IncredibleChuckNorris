package com.incredible.chuck.norris.view_model

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.incredible.chuck.norris.utils.Constants.ONBOARDING_FLAG

class OnboardingViewModel(private val preferences: SharedPreferences) : ViewModel() {

    fun setOnboardingFlag() = with(preferences.edit()) {
        putBoolean(ONBOARDING_FLAG, true)
        apply()
    }
}