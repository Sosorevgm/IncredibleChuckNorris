package com.incredible.chuck.norris.di

import com.incredible.chuck.norris.data.category_datasource.CategoryDataSource
import com.incredible.chuck.norris.data.category_datasource.CategoryRetrofitImplementation
import com.incredible.chuck.norris.data.fact_datasource.FactDataSource
import com.incredible.chuck.norris.data.fact_datasource.FactRetrofitImplementation
import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.view_model.CategoryViewModel
import com.incredible.chuck.norris.view_model.FactViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    factory <CategoryDataSource<List<String>>> {
        CategoryRetrofitImplementation()
    }

    factory<FactDataSource<FactModel>> {
        FactRetrofitImplementation()
    }
}

val viewModelDependency = module {
    viewModel {
        CategoryViewModel()
    }

    viewModel {
        FactViewModel()
    }
}