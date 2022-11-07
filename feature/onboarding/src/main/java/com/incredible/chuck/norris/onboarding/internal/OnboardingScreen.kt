package com.incredible.chuck.norris.onboarding.internal

import android.content.Context
import android.content.Intent
import ru.terrakok.cicerone.android.support.SupportAppScreen

class OnboardingScreen() : SupportAppScreen() {
    override fun getActivityIntent(context: Context?) =
        Intent(context, OnboardingActivity::class.java)
}
