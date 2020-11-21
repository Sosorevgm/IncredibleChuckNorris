package com.incredible.chuck.norris.data.repository

import com.incredible.chuck.norris.data.category_datasource.CategoryCacheImplementation
import com.incredible.chuck.norris.data.category_datasource.CategoryRetrofitImplementation
import com.incredible.chuck.norris.data.network.NetworkStatus
import com.incredible.chuck.norris.data.screen_state.CategoryScreenState

class CategoriesRepository(
    private val network: NetworkStatus,
    private val retrofit: CategoryRetrofitImplementation,
    private val cache: CategoryCacheImplementation
) {

    suspend fun getCategories(): CategoryScreenState {
        return if (network.status.value!!) {
            val categories = retrofit.getCategories()
            cache.putCategories(categories)
            CategoryScreenState.SuccessFromApi(categories)
        } else {
            cache.getCategories()
        }
    }
}