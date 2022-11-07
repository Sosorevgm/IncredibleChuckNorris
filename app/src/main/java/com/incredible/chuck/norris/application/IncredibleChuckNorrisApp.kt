package com.incredible.chuck.norris.application

import android.app.Application
import com.incredible.chuck.norris.dagger.AppComponent
import com.incredible.chuck.norris.dagger.DaggerAppComponent
import timber.log.Timber

class IncredibleChuckNorrisApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        Timber.plant(Timber.DebugTree())
    }
}
