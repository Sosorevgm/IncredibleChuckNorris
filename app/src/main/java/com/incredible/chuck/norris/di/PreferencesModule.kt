package com.incredible.chuck.norris.di

import android.content.Context
import com.incredible.chuck.norris.utils.Constants.ONBOARDING_PREFERENCES
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val preferences = module {

    single(named(ONBOARDING_PREFERENCES)) {
        androidApplication().getSharedPreferences(ONBOARDING_PREFERENCES, Context.MODE_PRIVATE)
    }

}

