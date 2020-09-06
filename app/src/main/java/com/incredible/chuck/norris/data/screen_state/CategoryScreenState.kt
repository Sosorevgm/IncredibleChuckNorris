package com.incredible.chuck.norris.data.screen_state

sealed class CategoryScreenState {
    data class Success(val categories: List<String>) : CategoryScreenState()
    object Loading : CategoryScreenState()
    data class Error(val error: String) : CategoryScreenState()
}