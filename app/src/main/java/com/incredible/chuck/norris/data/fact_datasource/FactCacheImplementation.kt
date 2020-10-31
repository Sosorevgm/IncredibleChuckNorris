package com.incredible.chuck.norris.data.fact_datasource

import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.data.room.AppDatabase

class FactCacheImplementation(
    private val room: AppDatabase
) : FactDataSource<FactModel> {

    override suspend fun getFact(category: String): FactModel {
        val entity = room.appDao.getFactByCategory(category)
        return FactModel(entity.id, entity.url, entity.iconUrl, entity.value, entity.date)
    }
}