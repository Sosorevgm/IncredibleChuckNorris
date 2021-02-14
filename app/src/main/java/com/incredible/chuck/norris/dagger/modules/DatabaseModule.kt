package com.incredible.chuck.norris.dagger.modules

import android.content.Context
import androidx.room.Room
import com.incredible.chuck.norris.data.room.AppDatabase
import com.incredible.chuck.norris.utils.Constants.APP_DATABASE
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesAppDatabase(context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        APP_DATABASE
    ).build()

    @Singleton
    @Provides
    fun providesDao(
        roomDatabase: AppDatabase
    ) = roomDatabase.appDao
}