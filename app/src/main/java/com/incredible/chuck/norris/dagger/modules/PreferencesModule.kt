package com.incredible.chuck.norris.dagger.modules

import android.content.Context
import com.incredible.chuck.norris.utils.Constants.ONBOARDING_PREFERENCES
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {

    @Provides
    fun providesPreferences(context: Context) =
        context.getSharedPreferences(ONBOARDING_PREFERENCES, Context.MODE_PRIVATE)
}