package com.incredible.chuck.norris.dagger.modules

import android.content.Context
import com.incredible.chuck.norris.data.image_datasource.GlideImageLoader
import com.incredible.chuck.norris.data.network.AndroidNetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesContext(context: Context): Context = context

    @Singleton
    @Provides
    fun providesGlide(context: Context) = GlideImageLoader(context)

    @Singleton
    @Provides
    fun providesAndroidNetworkStatus(context: Context) = AndroidNetworkStatus(context)
}
