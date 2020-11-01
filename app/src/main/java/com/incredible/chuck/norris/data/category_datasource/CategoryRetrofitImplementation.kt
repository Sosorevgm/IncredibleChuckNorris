package com.incredible.chuck.norris.data.category_datasource

import com.incredible.chuck.norris.data.api.ChuckNorrisApi

class CategoryRetrofitImplementation(
    private val retrofit: ChuckNorrisApi
) : CategoryDataSource<List<String>> {

    override suspend fun getCategories() = retrofit.getCategories()
}