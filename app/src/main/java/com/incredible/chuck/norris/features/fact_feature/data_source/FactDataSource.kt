package com.incredible.chuck.norris.features.fact_feature.data_source

interface FactDataSource<T> {
    suspend fun getFact(category: String): T
}