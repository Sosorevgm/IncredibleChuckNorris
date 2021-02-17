package com.incredible.chuck.norris.dagger.modules

import com.incredible.chuck.norris.data.api.ChuckNorrisApi
import com.incredible.chuck.norris.data.network.NetworkStatus
import com.incredible.chuck.norris.data.room.AppDatabase
import com.incredible.chuck.norris.features.categories_feature.data_source.CategoriesRepository
import com.incredible.chuck.norris.features.categories_feature.data_source.CategoryCacheImplementation
import com.incredible.chuck.norris.features.categories_feature.data_source.CategoryRetrofitImplementation
import com.incredible.chuck.norris.features.fact_feature.data_source.FactCacheImplementation
import com.incredible.chuck.norris.features.fact_feature.data_source.FactRepository
import com.incredible.chuck.norris.features.fact_feature.data_source.FactRetrofitImplementation
import com.sosorevgm.profanityfilter.ProfanityFilter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesFactRetrofitImplementation(retrofit: ChuckNorrisApi) =
        FactRetrofitImplementation(retrofit)

    @Singleton
    @Provides
    fun providesCategoryRetrofitImplementation(retrofit: ChuckNorrisApi) =
        CategoryRetrofitImplementation(retrofit)

    @Singleton
    @Provides
    fun providesFactCacheImplementation(database: AppDatabase) =
        FactCacheImplementation(database)

    @Singleton
    @Provides
    fun providesCategoryCacheImplementation(database: AppDatabase) =
        CategoryCacheImplementation(database)

    @Singleton
    @Provides
    fun providesFactRepository(
        networkStatus: NetworkStatus,
        retrofit: FactRetrofitImplementation,
        cache: FactCacheImplementation,
        profanityFilter: ProfanityFilter
    ) = FactRepository(
        networkStatus,
        retrofit,
        cache,
        profanityFilter
    )

    @Singleton
    @Provides
    fun providesCategoriesRepository(
        networkStatus: NetworkStatus,
        retrofit: CategoryRetrofitImplementation,
        cache: CategoryCacheImplementation
    ) = CategoriesRepository(
        networkStatus,
        retrofit,
        cache
    )
}