package com.incredible.chuck.norris.di

import com.incredible.chuck.norris.utils.Constants.ONBOARDING_PREFERENCES
import com.incredible.chuck.norris.view_model.CategoryViewModel
import com.incredible.chuck.norris.view_model.FactViewModel
import com.incredible.chuck.norris.view_model.OnboardingViewModel
import com.incredible.chuck.norris.view_model.SplashViewModel
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
        CategoryViewModel(network = get(), repository = get())
    }

    viewModel {
        FactViewModel(networkStatus = get(), repository = get(), profanityFilter = get())
    }
}