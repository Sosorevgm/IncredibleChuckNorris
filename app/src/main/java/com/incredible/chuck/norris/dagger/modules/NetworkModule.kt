package com.incredible.chuck.norris.dagger.modules

import com.incredible.chuck.norris.data.network.AndroidNetworkStatus
import com.incredible.chuck.norris.data.network.NetworkStatus
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class NetworkModule {

    @Singleton
    @Binds
    abstract fun providesNetworkStatus(status: AndroidNetworkStatus): NetworkStatus
}