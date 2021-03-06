package com.incredible.chuck.norris.features.onboarding_feature

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.incredible.chuck.norris.utils.Constants.ONBOARDING_FLAG
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val preferences: SharedPreferences
) : ViewModel() {

    fun finishOnboarding() {
        preferences.edit().putBoolean(ONBOARDING_FLAG, true).apply()
    }
}