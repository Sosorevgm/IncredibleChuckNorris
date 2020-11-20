package com.incredible.chuck.norris.view_model

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.incredible.chuck.norris.utils.Constants.ONBOARDING_FLAG

class SplashViewModel(private val preferences: SharedPreferences) : ViewModel() {

    fun checkOnboarding() = preferences.getBoolean(ONBOARDING_FLAG, false)
}