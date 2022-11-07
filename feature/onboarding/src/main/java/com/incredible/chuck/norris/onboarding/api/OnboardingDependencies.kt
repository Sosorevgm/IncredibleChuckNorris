package com.incredible.chuck.norris.onboarding.api

import android.content.Context
import android.content.Intent

interface OnboardingDependencies {
    fun context(): Context
    fun mainActivityIntent(): Intent
}
