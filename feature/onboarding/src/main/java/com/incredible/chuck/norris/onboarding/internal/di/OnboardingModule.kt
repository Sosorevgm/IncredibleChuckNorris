package com.incredible.chuck.norris.onboarding.internal.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class OnboardingModule {

    @Provides
    @OnboardingScope
    fun context(context: Context): Context = context
}
