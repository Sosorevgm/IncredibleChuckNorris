package com.incredible.chuck.norris.data.repository

import com.incredible.chuck.norris.data.fact_datasource.FactCacheImplementation
import com.incredible.chuck.norris.data.fact_datasource.FactRetrofitImplementation
import com.incredible.chuck.norris.data.network.AndroidNetworkStatus

class FactRepository(
    private val retrofit: FactRetrofitImplementation,
    private val cache: FactCacheImplementation
) {

    suspend fun  getFact(category: String) = retrofit.getFact(category)

}