package com.incredible.chuck.norris.model

sealed class CategoryScreenState {
    data class Success(val categories: List<String>) : CategoryScreenState()
    object Loading : CategoryScreenState()
    data class Error(val error: String) : CategoryScreenState()
}