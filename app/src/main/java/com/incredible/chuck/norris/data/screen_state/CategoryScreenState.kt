package com.incredible.chuck.norris.data.screen_state

sealed class CategoryScreenState {
    data class SuccessFromApi(val categories: List<String>) : CategoryScreenState()
    data class SuccessFromCache(val categories: List<String>) : CategoryScreenState()
    object Loading : CategoryScreenState()
    object Error : CategoryScreenState()
}