package com.incredible.chuck.norris.data.category_datasource

import com.incredible.chuck.norris.data.room.AppDatabase
import com.incredible.chuck.norris.data.room.CategoryEntity
import com.incredible.chuck.norris.data.screen_state.CategoryScreenState

class CategoryCacheImplementation(
    private val room: AppDatabase
) : CategoryDataSource<CategoryScreenState> {

    override suspend fun getCategories(): CategoryScreenState {
        val categories = room.appDao.getCategories().map { it.category }
        return if (categories.isNotEmpty()) {
            CategoryScreenState.SuccessFromCache(categories)
        } else {
            CategoryScreenState.ErrorCacheIsEmpty("Categories cache is empty. Try to find a connection.")
        }
    }

    suspend fun putCategories(categories: List<String>) {
        room.appDao.putCategories(categories.map { CategoryEntity(it) })
    }
}