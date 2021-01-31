package com.incredible.chuck.norris.features.categories_feature.data_source

interface CategoryDataSource<T> {
    suspend fun getCategories(): T
}