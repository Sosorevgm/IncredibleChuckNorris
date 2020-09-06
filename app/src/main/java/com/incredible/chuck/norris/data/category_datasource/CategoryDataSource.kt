package com.incredible.chuck.norris.data.category_datasource

interface CategoryDataSource<T> {
    suspend fun getData(): T
}