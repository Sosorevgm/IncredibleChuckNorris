package com.incredible.chuck.norris.onboarding.internal

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.incredible.chuck.norris.onboarding.internal.OnboardingConstants.ONBOARDING_FLAG
import javax.inject.Inject

class OnboardingViewModel(
    private val preferences: SharedPreferences
) : ViewModel() {

    fun finishOnboarding() {
        preferences.edit().putBoolean(ONBOARDING_FLAG, true).apply()
    }
}

class OnboardingViewModelFactory @Inject constructor(
    private val preferences: SharedPreferences
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == OnboardingViewModel::class.java)
        return OnboardingViewModel(
            preferences
        ) as T
    }
}
