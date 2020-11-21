package com.incredible.chuck.norris.di

import com.incredible.chuck.norris.data.image_datasource.GlideImageLoader
import com.incredible.chuck.norris.data.image_datasource.ImageLoader
import com.sosorevgm.profanityfilter.ProfanityFilter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val utils = module {

    single<ImageLoader> {
        GlideImageLoader(context = androidContext())
    }

    single {
        ProfanityFilter(context = get())
    }
}