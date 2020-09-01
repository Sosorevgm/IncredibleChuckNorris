package com.incredible.chuck.norris.model.datasource

import com.incredible.chuck.norris.model.api.ChuckNorrisApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryRetrofitImplementation : DataSource<List<String>> {

    companion object {
        const val BASE_URL = "https://api.chucknorris.io/jokes/categories/"
    }

    override suspend fun getData(): List<String> {
        return createRetrofit().create(ChuckNorrisApi::class.java).getCategories()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}