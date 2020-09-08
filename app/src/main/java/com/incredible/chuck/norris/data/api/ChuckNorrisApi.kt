package com.incredible.chuck.norris.data.api

import com.incredible.chuck.norris.data.models.FactModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisApi {
    @GET(".")
    suspend fun getCategories(): List<String>

    @GET(".")
    fun getFactByCategoryAsync(@Query("category") category: String): Deferred<FactModel>
}