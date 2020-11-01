package com.incredible.chuck.norris.data.repository

import com.incredible.chuck.norris.data.category_datasource.CategoryCacheImplementation
import com.incredible.chuck.norris.data.category_datasource.CategoryRetrofitImplementation
import com.incredible.chuck.norris.data.network.AndroidNetworkStatus

class CategoriesRepository(
    private val retrofit: CategoryRetrofitImplementation,
    private val cache: CategoryCacheImplementation
) {
    suspend fun getCategories(): List<String> {
        return retrofit.getCategories()
    }

    suspend fun putCategoriesInCache(categories: List<String>) {
        cache.putCategories(categories)
    }

    suspend fun getCategoriesFromCache() = cache.getCategories()
}