package com.incredible.chuck.norris.di

import com.incredible.chuck.norris.features.categories_feature.CategoryViewModel
import com.incredible.chuck.norris.features.fact_feature.FactViewModel
import com.incredible.chuck.norris.features.main.MainViewModel
import com.incredible.chuck.norris.features.onboarding_feature.OnboardingViewModel
import com.incredible.chuck.norris.features.splash_feature.SplashViewModel
import com.incredible.chuck.norris.utils.Constants.ONBOARDING_PREFERENCES
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModels = module {

    viewModel {
        SplashViewModel(preferences = get(named(ONBOARDING_PREFERENCES)))
    }

    viewModel {
        OnboardingViewModel(preferences = get(named(ONBOARDING_PREFERENCES)))
    }

    viewModel {
        MainViewModel(router = get())
    }

    viewModel {
        CategoryViewModel(repository = get(), router = get())
    }

    viewModel {
        FactViewModel(repository = get(), router = get())
    }
}