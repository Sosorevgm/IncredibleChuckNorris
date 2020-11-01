package com.incredible.chuck.norris.data.category_datasource

import com.incredible.chuck.norris.data.room.AppDatabase
import com.incredible.chuck.norris.data.room.CategoryEntity

class CategoryCacheImplementation(
    private val room: AppDatabase
) : CategoryDataSource<List<String>> {

    override suspend fun getCategories(): List<String> {
        return room.appDao.getCategories().map { it.category }
    }

    suspend fun putCategories(categories: List<String>) {
        room.appDao.putCategories(categories.map { CategoryEntity(it) })
    }
}