package com.incredible.chuck.norris.data.fact_datasource

import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.data.room.AppDatabase
import com.incredible.chuck.norris.data.room.FactEntity

class FactCacheImplementation(
    private val room: AppDatabase
) {

    suspend fun getRandomFactByCategory(category: String): FactModel {
        val entities = room.appDao.getAllFactsByCategory(category)
        val randomEntity = entities[(0..entities.size).random()]
        return FactModel(
            randomEntity.id,
            randomEntity.url,
            randomEntity.iconUrl,
            randomEntity.value,
            randomEntity.date
        )
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