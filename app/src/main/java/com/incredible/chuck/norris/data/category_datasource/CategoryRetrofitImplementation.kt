package com.incredible.chuck.norris.data.category_datasource

import com.incredible.chuck.norris.data.api.ChuckNorrisApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryRetrofitImplementation :
    CategoryDataSource<List<String>> {

    companion object {
        private const val BASE_URL = "https://api.chucknorris.io/jokes/categories/"
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