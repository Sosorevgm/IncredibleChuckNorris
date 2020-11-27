package com.incredible.chuck.norris.di

import androidx.room.Room
import com.incredible.chuck.norris.BuildConfig
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
import com.incredible.chuck.norris.utils.Constants.APP_DATABASE
import com.incredible.chuck.norris.utils.Constants.CHUCK_BASE_URL
import com.incredible.chuck.norris.utils.Constants.LOGGING_INTERCEPTOR
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val repositories = module {

    single<NetworkStatus> {
        AndroidNetworkStatus(context = androidContext())
    }

    single<Interceptor>(named(LOGGING_INTERCEPTOR)) {
        HttpLoggingInterceptor().also {
            it.level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single {
        OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(get<Interceptor>(named(LOGGING_INTERCEPTOR)))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(CHUCK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.Companion())
            .client(get())
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
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, APP_DATABASE).build()
    }

    single {
        FactCacheImplementation(room = get())
    }

    single {
        CategoryCacheImplementation(room = get())
    }

    single {
        FactRepository(retrofit = get(), cache = get(), network = get(), profanityFilter = get())
    }

    single {
        CategoriesRepository(network = get(), retrofit = get(), cache = get())
    }
}