package com.incredible.chuck.norris.dagger.modules

import android.content.Context
import com.incredible.chuck.norris.data.network.AndroidNetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesNetworkStatus(context: Context) = AndroidNetworkStatus(context)
}