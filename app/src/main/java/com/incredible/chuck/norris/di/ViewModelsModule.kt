package com.incredible.chuck.norris.di

import com.incredible.chuck.norris.utils.Constants.ONBOARDING_PREFERENCES
import com.incredible.chuck.norris.view_model.*
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