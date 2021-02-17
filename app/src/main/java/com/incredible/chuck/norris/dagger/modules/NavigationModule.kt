package com.incredible.chuck.norris.dagger.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {

    @Singleton
    @Provides
    fun providesCiceroneInstance(): Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun providesCiceroneHolder(cicerone: Cicerone<Router>) = cicerone.navigatorHolder

    @Singleton
    @Provides
    fun providesCiceroneRouter(cicerone: Cicerone<Router>) = cicerone.router
}