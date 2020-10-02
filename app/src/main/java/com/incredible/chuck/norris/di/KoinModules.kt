package com.incredible.chuck.norris.di

import com.incredible.chuck.norris.data.category_datasource.CategoryDataSource
import com.incredible.chuck.norris.data.category_datasource.CategoryRetrofitImplementation
import com.incredible.chuck.norris.data.fact_datasource.FactDataSource
import com.incredible.chuck.norris.data.fact_datasource.FactRetrofitImplementation
import com.incredible.chuck.norris.data.image_datasource.GlideImageLoader
import com.incredible.chuck.norris.data.image_datasource.ImageLoader
import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.utils.ProfanityFilter
import com.incredible.chuck.norris.view_model.CategoryViewModel
import com.incredible.chuck.norris.view_model.FactViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    factory<CategoryDataSource<List<String>>> {
        CategoryRetrofitImplementation()
    }

    factory<FactDataSource<FactModel>> {
        FactRetrofitImplementation()
    }

    single<ImageLoader> {
        GlideImageLoader(androidContext())
    }

    single {
        ProfanityFilter(get())
    }
}

val viewModelDependency = module {
    viewModel {
        CategoryViewModel(get())
    }

    viewModel {
        FactViewModel(get(), get())
    }
}