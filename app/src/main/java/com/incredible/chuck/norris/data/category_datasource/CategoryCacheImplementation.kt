package com.incredible.chuck.norris.data.category_datasource

import com.incredible.chuck.norris.data.room.AppDatabase

class CategoryCacheImplementation(
    private val room: AppDatabase
) : CategoryDataSource<List<String>> {

    override suspend fun getCategories(): List<String> {
        return room.appDao.getCategories().map { it.category }
    }
}