package com.incredible.chuck.norris.dagger.modules

import com.incredible.chuck.norris.features.categories_feature.CategoryFragment
import com.incredible.chuck.norris.features.fact_feature.FactFragment
import com.incredible.chuck.norris.features.main.MainActivity
import com.incredible.chuck.norris.features.onboarding_feature.OnboardingActivity
import com.incredible.chuck.norris.features.splash_feature.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun injectSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun injectOnboardingActivity(): OnboardingActivity

    @ContributesAndroidInjector(modules = [AppFragmentsModule::class])
    abstract fun injectMainActivityFragments(): MainActivity

}

@Module
abstract class AppFragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeCategoriesFragment(): CategoryFragment

    @ContributesAndroidInjector
    abstract fun contributeFactFragment(): FactFragment
}