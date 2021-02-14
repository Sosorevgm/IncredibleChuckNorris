package com.incredible.chuck.norris.dagger.modules

import android.app.Application
import com.incredible.chuck.norris.application.IncredibleChuckNorrisApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
    private val application: IncredibleChuckNorrisApp
) {

    @Provides
    @Singleton
    fun providesApplication(): Application = application
}