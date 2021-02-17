package com.incredible.chuck.norris.dagger.modules

import com.incredible.chuck.norris.features.main.MainActivity
import com.incredible.chuck.norris.features.onboarding_feature.OnboardingActivity
import com.incredible.chuck.norris.features.splash_feature.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributeOnboardingActivity(): OnboardingActivity

    @ContributesAndroidInjector(
        modules = [
            FragmentBuildersModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

}