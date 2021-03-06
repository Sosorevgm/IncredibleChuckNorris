package com.incredible.chuck.norris.features.fact_feature.data_source

import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.data.room.AppDatabase
import com.incredible.chuck.norris.data.room.FactEntity
import com.incredible.chuck.norris.features.fact_feature.FactScreenState
import com.incredible.chuck.norris.common.ErrorModel
import javax.inject.Inject

class FactCacheImplementation @Inject constructor(
    private val room: AppDatabase
) {

    suspend fun getRandomFactByCategory(category: String): FactScreenState {
        val entities = room.appDao.getAllFactsByCategory(category)
        return if (entities.isNotEmpty()) {
            val randomValue = (entities.indices).random()
            val randomEntity = entities[randomValue]
            val fact = FactModel(
                randomEntity.id,
                randomEntity.url,
                randomEntity.iconUrl,
                randomEntity.value,
                randomEntity.date
            )
            FactScreenState.SuccessFromCache(fact)
        } else {
            FactScreenState.Error(
                ErrorModel(
                    messageResId = R.string.facts_from_cache_is_empty,
                    buttonTextResId = R.string.retry
                )
            )
        }
    }

    suspend fun putFact(fact: FactModel, category: String) =
        room.appDao.putFact(
            FactEntity(
                fact.id,
                category,
                fact.url,
                fact.iconUrl,
                fact.fact,
                fact.date
            )
        )
}