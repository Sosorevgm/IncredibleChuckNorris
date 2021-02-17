package com.incredible.chuck.norris.dagger.modules

import com.incredible.chuck.norris.features.main.MainActivity
import com.incredible.chuck.norris.features.onboarding_feature.OnboardingActivity
import com.incredible.chuck.norris.features.splash_feature.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelsModule::class
        ]
    )
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(
        modules = [
            ViewModelsModule::class
        ]
    )
    abstract fun contributeOnboardingActivity(): OnboardingActivity

    @ContributesAndroidInjector(
        modules = [
            FragmentBuildersModule::class,
            ViewModelsModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

}