package com.incredible.chuck.norris.application

import com.incredible.chuck.norris.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class IncredibleChuckNorrisApp : DaggerApplication() {

//    @Inject
//    lateinit var injector: DispatchingAndroidInjector<Any>

//    val appComponent: AppComponent
//        get() = component
//
//    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

//        component = DaggerAppComponent.builder()
//            .application(this)
//            .appModule(AppModule(this))
//            .build()
//
//        component.inject(this)

        Timber.plant(Timber.DebugTree())
    }

//    override fun androidInjector(): AndroidInjector<Any> {
//        return injector
//    }

    override fun applicationInjector(): AndroidInjector<IncredibleChuckNorrisApp> {
        return DaggerAppComponent.factory().create(this)
    }
}