package com.incredible.chuck.norris.model.datasource

interface DataSource<T> {
    suspend fun getData(): T
}