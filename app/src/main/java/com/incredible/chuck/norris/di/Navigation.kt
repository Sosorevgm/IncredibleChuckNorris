package com.incredible.chuck.norris.di

import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

val navigation = module {

    single<Cicerone<Router>> {
        Cicerone.create()
    }

    single {
        get<Cicerone<Router>>().navigatorHolder
    }

    single {
        get<Cicerone<Router>>().router
    }
}