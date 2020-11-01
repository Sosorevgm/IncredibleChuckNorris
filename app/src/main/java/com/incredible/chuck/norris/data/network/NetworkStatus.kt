package com.incredible.chuck.norris.data.network

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class NetworkStatus {

    abstract val status: MutableLiveData<Boolean>

    protected val coroutineScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob()
    )

}