package com.incredible.chuck.norris.dagger

import android.app.Application
import com.incredible.chuck.norris.application.IncredibleChuckNorrisApp
import com.incredible.chuck.norris.dagger.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        NavigationModule::class,
        PreferencesModule::class,
        UtilsModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        RetrofitModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent : AndroidInjector<IncredibleChuckNorrisApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}
