package com.incredible.chuck.norris.features.fact_feature.data_source

import com.incredible.chuck.norris.features.fact_feature.data_source.FactCacheImplementation
import com.incredible.chuck.norris.features.fact_feature.data_source.FactRetrofitImplementation
import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.data.network.NetworkStatus
import com.incredible.chuck.norris.features.fact_feature.FactScreenState
import com.sosorevgm.profanityfilter.ProfanityFilter

class FactRepository(
    private val network: NetworkStatus,
    private val retrofit: FactRetrofitImplementation,
    private val cache: FactCacheImplementation,
    private val profanityFilter: ProfanityFilter
) {

    init {
        profanityFilter.addSwearwords("dickfat")
    }

    suspend fun getFact(category: String): FactScreenState {
        return if (network.status.value!!) {
            val fact = applyProfanityFilter(retrofit.getFact(category))
            cache.putFact(fact, category)
            FactScreenState.SuccessFromApi(fact)
        } else {
            return cache.getRandomFactByCategory(category)
        }
    }

    private fun applyProfanityFilter(fact: FactModel): FactModel {
        val filteredFact = profanityFilter.getFilteredString(fact.fact)
        fact.fact = filteredFact
        return fact
    }
}