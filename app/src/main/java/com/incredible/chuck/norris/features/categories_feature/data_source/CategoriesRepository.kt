package com.incredible.chuck.norris.features.categories_feature.data_source

import com.incredible.chuck.norris.features.categories_feature.data_source.CategoryCacheImplementation
import com.incredible.chuck.norris.features.categories_feature.data_source.CategoryRetrofitImplementation
import com.incredible.chuck.norris.data.network.NetworkStatus
import com.incredible.chuck.norris.features.categories_feature.CategoryScreenState
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
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