package com.incredible.chuck.norris.features.categories_feature

import com.incredible.chuck.norris.common.ErrorModel

sealed class CategoryScreenState {
    data class SuccessFromApi(val categories: List<String>) : CategoryScreenState()
    data class SuccessFromCache(val categories: List<String>) : CategoryScreenState()
    object Loading : CategoryScreenState()
    data class Error(val errorModel: ErrorModel) : CategoryScreenState()
}