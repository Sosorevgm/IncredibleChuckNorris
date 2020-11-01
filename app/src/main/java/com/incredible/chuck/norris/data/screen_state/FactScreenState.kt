package com.incredible.chuck.norris.data.screen_state

import com.incredible.chuck.norris.data.models.FactModel

sealed class FactScreenState {
    object Loading : FactScreenState()
    data class SuccessFromApi(val fact: FactModel) : FactScreenState()
    data class SuccessFromCache(val fact: FactModel) : FactScreenState()
    data class Error(val error: String) : FactScreenState()
    data class ErrorCacheIsEmpty(val error: String) : FactScreenState()
}