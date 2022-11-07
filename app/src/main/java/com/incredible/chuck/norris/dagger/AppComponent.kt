package com.incredible.chuck.norris.dagger

import android.content.Context
import com.incredible.chuck.norris.dagger.modules.*
import com.incredible.chuck.norris.features.categories_feature.CategoryFragment
import com.incredible.chuck.norris.features.fact_feature.FactFragment
import com.incredible.chuck.norris.features.main.MainActivity
import com.incredible.chuck.norris.features.onboarding_feature.OnboardingActivity
import com.incredible.chuck.norris.features.splash_feature.SplashActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        NavigationModule::class,
        PreferencesModule::class,
        ImageLoaderModule::class,
        UtilsModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        RetrofitModule::class,
        RepositoryModule::class,
        ViewModelsModule::class
    ]
)
interface AppComponent {

    fun inject(activity: SplashActivity)
    fun inject(activity: OnboardingActivity)
    fun inject(activity: MainActivity)
    fun inject(fragment: FactFragment)
    fun inject(fragment: CategoryFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
