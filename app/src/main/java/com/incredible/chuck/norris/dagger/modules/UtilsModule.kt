package com.incredible.chuck.norris.dagger.modules

import android.content.Context
import com.sosorevgm.profanityfilter.ProfanityFilter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Singleton
    @Provides
    fun providesProfanityFilter(context: Context) = ProfanityFilter(context)
}
