package com.incredible.chuck.norris.data.category_datasource

import com.incredible.chuck.norris.data.room.AppDatabase
import com.incredible.chuck.norris.data.room.CategoryEntity
import com.incredible.chuck.norris.exceptions.CategoriesCacheIsEmpty

class CategoryCacheImplementation(
    private val room: AppDatabase
) : CategoryDataSource<List<String>> {

    override suspend fun getCategories(): List<String> {
        val categories = room.appDao.getCategories().map { it.category }
        if (categories.isNotEmpty()) {
            return categories
        }
        throw CategoriesCacheIsEmpty("Categories cache is empty. Try to find a connection.")
    }

    suspend fun putCategories(categories: List<String>) {
        room.appDao.putCategories(categories.map { CategoryEntity(it) })
    }
}