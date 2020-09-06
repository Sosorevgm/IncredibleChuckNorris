package com.incredible.chuck.norris.data.fact_datasource

interface FactDataSource<T> {
    suspend fun getData(category: String): T
}