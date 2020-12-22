package com.incredible.chuck.norris.data.screen_state

import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.data.view.ErrorModel

sealed class FactScreenState {
    object Loading : FactScreenState()
    data class SuccessFromApi(val fact: FactModel) : FactScreenState()
    data class SuccessFromCache(val fact: FactModel) : FactScreenState()
    data class Error(val errorModel: ErrorModel) : FactScreenState()
}