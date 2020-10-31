package com.incredible.chuck.norris.data.repository

import com.incredible.chuck.norris.data.category_datasource.CategoryCacheImplementation
import com.incredible.chuck.norris.data.category_datasource.CategoryRetrofitImplementation

class CategoriesRepository(
    private val retrofit: CategoryRetrofitImplementation,
    private val cache: CategoryCacheImplementation
) {
    suspend fun getCategories() = retrofit.getCategories()
}