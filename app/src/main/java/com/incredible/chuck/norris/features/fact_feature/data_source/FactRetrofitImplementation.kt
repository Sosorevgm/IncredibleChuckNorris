package com.incredible.chuck.norris.features.fact_feature.data_source

import com.incredible.chuck.norris.data.api.ChuckNorrisApi
import com.incredible.chuck.norris.data.models.FactModel
import javax.inject.Inject

class FactRetrofitImplementation @Inject constructor(
    private val retrofit: ChuckNorrisApi
) : FactDataSource<FactModel> {

    override suspend fun getFact(category: String) =
        retrofit.getFactByCategoryAsync(category).await()
}