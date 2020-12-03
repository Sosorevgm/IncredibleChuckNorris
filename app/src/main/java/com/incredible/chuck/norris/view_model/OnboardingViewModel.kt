package com.incredible.chuck.norris.view_model

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.incredible.chuck.norris.navigation.Screens
import com.incredible.chuck.norris.utils.Constants.ONBOARDING_FLAG
import ru.terrakok.cicerone.Router

class OnboardingViewModel(
    private val preferences: SharedPreferences
) : ViewModel() {

    fun finishOnboarding() {
        preferences.edit().putBoolean(ONBOARDING_FLAG, true).apply()
    }
}