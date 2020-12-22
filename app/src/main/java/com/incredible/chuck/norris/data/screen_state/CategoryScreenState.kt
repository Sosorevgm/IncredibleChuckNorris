package com.incredible.chuck.norris.data.screen_state

import com.incredible.chuck.norris.data.view.ErrorModel

sealed class CategoryScreenState {
    data class SuccessFromApi(val categories: List<String>) : CategoryScreenState()
    data class SuccessFromCache(val categories: List<String>) : CategoryScreenState()
    object Loading : CategoryScreenState()
    data class Error(val errorModel: ErrorModel) : CategoryScreenState()
}