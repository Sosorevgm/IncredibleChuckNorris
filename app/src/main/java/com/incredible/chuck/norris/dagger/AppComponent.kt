package com.incredible.chuck.norris.dagger

import android.app.Application
import com.incredible.chuck.norris.application.IncredibleChuckNorrisApp
import com.incredible.chuck.norris.dagger.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
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
//interface AppComponent {
//    fun inject(application: IncredibleChuckNorrisApp)
//
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: IncredibleChuckNorrisApp): Builder
//        fun appModule(module: AppModule): Builder
//        fun build(): AppComponent
//    }
//}
