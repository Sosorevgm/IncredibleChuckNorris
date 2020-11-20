package com.incredible.chuck.norris.di

import androidx.room.Room
import com.incredible.chuck.norris.data.api.ChuckNorrisApi
import com.incredible.chuck.norris.data.category_datasource.CategoryCacheImplementation
import com.incredible.chuck.norris.data.category_datasource.CategoryRetrofitImplementation
import com.incredible.chuck.norris.data.fact_datasource.FactCacheImplementation
import com.incredible.chuck.norris.data.fact_datasource.FactRetrofitImplementation
import com.incredible.chuck.norris.data.network.AndroidNetworkStatus
import com.incredible.chuck.norris.data.network.NetworkStatus
import com.incredible.chuck.norris.data.repository.CategoriesRepository
import com.incredible.chuck.norris.data.repository.FactRepository
import com.incredible.chuck.norris.data.room.AppDatabase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositories = module {

    single<NetworkStatus> {
        AndroidNetworkStatus(context = androidContext())
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.Companion())
            .build()
            .create(ChuckNorrisApi::class.java)
    }

    single {
        FactRetrofitImplementation(retrofit = get())
    }

    single {
        CategoryRetrofitImplementation(retrofit = get())
    }

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_database").build()
    }

    single {
        FactCacheImplementation(room = get())
    }

    single {
        CategoryCacheImplementation(room = get())
    }

    single {
        FactRepository(retrofit = get(), cache = get())
    }

    single {
        CategoriesRepository(retrofit = get(), cache = get())
    }
}