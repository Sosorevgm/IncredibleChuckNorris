package com.incredible.chuck.norris.dagger.modules

import android.app.Application
import android.content.Context
import com.incredible.chuck.norris.data.image_datasource.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun providesGlide(context: Context) = GlideImageLoader(context)
}