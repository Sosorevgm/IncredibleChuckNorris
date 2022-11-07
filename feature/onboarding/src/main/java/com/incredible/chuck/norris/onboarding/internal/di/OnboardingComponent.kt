package com.incredible.chuck.norris.onboarding.internal.di

import com.incredible.chuck.norris.onboarding.api.OnboardingDependencies
import com.incredible.chuck.norris.onboarding.internal.OnboardingActivity
import dagger.Component

@Component(
    modules = [OnboardingModule::class],
    dependencies = [OnboardingDependencies::class]
)
interface OnboardingComponent {
    fun inject(activity: OnboardingActivity)
}
