package com.incredible.chuck.norris.di

import com.incredible.chuck.norris.view_model.CategoryViewModel
import com.incredible.chuck.norris.view_model.FactViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {

}

val viewModelDependency = module {
    viewModel {
        CategoryViewModel()
    }

    viewModel {
        FactViewModel()
    }
}