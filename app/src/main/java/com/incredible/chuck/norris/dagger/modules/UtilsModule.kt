package com.incredible.chuck.norris.dagger.modules

import android.content.Context
import com.incredible.chuck.norris.data.image_datasource.GlideImageLoader
import com.sosorevgm.profanityfilter.ProfanityFilter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Singleton
    @Provides
    fun providesImageLoader(context: Context) = GlideImageLoader(context)

    @Singleton
    @Provides
    fun providesProfanityFilter(context: Context) = ProfanityFilter(context)
}
