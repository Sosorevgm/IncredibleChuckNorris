package com.incredible.chuck.norris.features.fact_feature.data_source

import com.incredible.chuck.norris.data.api.ChuckNorrisApi
import com.incredible.chuck.norris.data.models.FactModel

class FactRetrofitImplementation(
    private val retrofit: ChuckNorrisApi
) : FactDataSource<FactModel> {

    override suspend fun getFact(category: String) =
        retrofit.getFactByCategoryAsync(category).await()
}