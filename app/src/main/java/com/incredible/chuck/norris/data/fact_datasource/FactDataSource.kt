package com.incredible.chuck.norris.data.fact_datasource

interface FactDataSource<T> {
    suspend fun getFact(category: String): T
}