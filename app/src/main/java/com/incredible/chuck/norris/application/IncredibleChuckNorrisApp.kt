package com.incredible.chuck.norris.application

import android.app.Application
import com.incredible.chuck.norris.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IncredibleChuckNorrisApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@IncredibleChuckNorrisApp)
            modules(listOf(preferences, repositories, utils, viewModels))
        }
    }
}