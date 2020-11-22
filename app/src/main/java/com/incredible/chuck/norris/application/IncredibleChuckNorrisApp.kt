package com.incredible.chuck.norris.application

import android.app.Application
import com.incredible.chuck.norris.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class IncredibleChuckNorrisApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@IncredibleChuckNorrisApp)
            modules(listOf(preferences, repositories, utils, viewModels))
        }
    }
}