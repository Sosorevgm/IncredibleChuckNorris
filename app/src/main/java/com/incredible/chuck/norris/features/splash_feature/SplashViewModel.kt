package com.incredible.chuck.norris.features.splash_feature

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.incredible.chuck.norris.utils.Constants.ONBOARDING_FLAG
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val preferences: SharedPreferences
) : ViewModel() {

    fun checkOnboarding() = preferences.getBoolean(ONBOARDING_FLAG, false)
}