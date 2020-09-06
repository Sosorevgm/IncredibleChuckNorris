package com.incredible.chuck.norris.data.fact_datasource

import com.incredible.chuck.norris.data.api.ChuckNorrisApi
import com.incredible.chuck.norris.data.models.FactModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FactRetrofitImplementation :
    FactDataSource<FactModel> {

    companion object {
        private const val BASE_URL = "https://api.chucknorris.io/jokes/random/"
    }

    override suspend fun getData(category: String): FactModel {
        return createRetrofit().create(ChuckNorrisApi::class.java).getFactByCategoryAsync(category)
            .await()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.Companion())
            .build()
    }
}