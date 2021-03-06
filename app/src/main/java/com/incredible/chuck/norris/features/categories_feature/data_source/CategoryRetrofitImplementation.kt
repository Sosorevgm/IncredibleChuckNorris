package com.incredible.chuck.norris.features.categories_feature.data_source

import com.incredible.chuck.norris.data.api.ChuckNorrisApi
import javax.inject.Inject

class CategoryRetrofitImplementation @Inject constructor(
    private val retrofit: ChuckNorrisApi
) : CategoryDataSource<List<String>> {

    override suspend fun getCategories() = retrofit.getCategories()
}