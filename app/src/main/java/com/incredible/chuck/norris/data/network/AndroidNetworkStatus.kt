package com.incredible.chuck.norris.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch

class AndroidNetworkStatus(
    context: Context
) : NetworkStatus() {

    override val status = MutableLiveData<Boolean>()

    init {
        status.value = false

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder = NetworkRequest.Builder()

        connectivityManager.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    coroutineScope.launch {
                        status.value = true
                    }
                }

                override fun onLost(network: Network) {
                    coroutineScope.launch {
                        status.value = false
                    }
                }

                override fun onUnavailable() {
                    coroutineScope.launch {
                        status.value = false
                    }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    coroutineScope.launch {
                        status.value = false
                    }
                }
            })
    }
}