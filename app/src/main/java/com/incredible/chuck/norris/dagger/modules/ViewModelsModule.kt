package com.incredible.chuck.norris.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.incredible.chuck.norris.dagger.factory.ViewModelKey
import com.incredible.chuck.norris.dagger.factory.ViewModelProviderFactory
import com.incredible.chuck.norris.features.categories_feature.CategoryViewModel
import com.incredible.chuck.norris.features.fact_feature.FactViewModel
import com.incredible.chuck.norris.features.main.MainViewModel
import com.incredible.chuck.norris.features.onboarding_feature.OnboardingViewModel
import com.incredible.chuck.norris.features.splash_feature.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelProviderFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun providesSplashViewModel(
        viewModel: SplashViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OnboardingViewModel::class)
    abstract fun providesOnboardingViewModel(
        viewModel: OnboardingViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun providesMainViewModel(
        viewModel: MainViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun providesCategoryViewModel(
        viewModel: CategoryViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FactViewModel::class)
    abstract fun providesFactViewModel(
        viewModel: FactViewModel
    ): ViewModel
}