package com.incredible.chuck.norris.model.api

import com.incredible.chuck.norris.model.FactModel
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisApi {

    @GET
    suspend fun getRandomFact(): Deferred<FactModel>

    @GET(".")
    suspend fun getCategories(): List<String>

    @GET
    suspend fun getFactByCategory(@Query("category") category: String): Deferred<FactModel>

}