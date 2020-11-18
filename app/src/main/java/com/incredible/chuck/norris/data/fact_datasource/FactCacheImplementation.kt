package com.incredible.chuck.norris.data.fact_datasource

import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.data.room.AppDatabase
import com.incredible.chuck.norris.data.room.FactEntity
import com.incredible.chuck.norris.exceptions.FactCacheIsEmptyException

class FactCacheImplementation(
    private val room: AppDatabase
) {

    suspend fun getRandomFactByCategory(category: String): FactModel {
        val entities = room.appDao.getAllFactsByCategory(category)
        if (entities.isNotEmpty()) {
            val randomValue = (entities.indices).random()
            val randomEntity = entities[randomValue]
            return FactModel(
                randomEntity.id,
                randomEntity.url,
                randomEntity.iconUrl,
                randomEntity.value,
                randomEntity.date
            )
        }
        throw FactCacheIsEmptyException("Cache from $category category is empty. Try to find a connection")
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