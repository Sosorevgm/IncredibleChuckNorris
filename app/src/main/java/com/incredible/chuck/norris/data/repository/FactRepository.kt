package com.incredible.chuck.norris.data.repository

import com.incredible.chuck.norris.data.fact_datasource.FactCacheImplementation
import com.incredible.chuck.norris.data.fact_datasource.FactRetrofitImplementation
import com.incredible.chuck.norris.data.models.FactModel

class FactRepository(
    private val retrofit: FactRetrofitImplementation,
    private val cache: FactCacheImplementation
) {

    suspend fun getFactFromApi(category: String) = retrofit.getFact(category)

    suspend fun getRandomFactFromCacheByCategory(category: String) =
        cache.getRandomFactByCategory(category)

    suspend fun putFact(fact: FactModel, category: String) = cache.putFact(fact, category)

}