package com.incredible.chuck.norris.features.fact_feature

import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.common.ErrorModel

sealed class FactScreenState {
    object Loading : FactScreenState()
    data class SuccessFromApi(val fact: FactModel) : FactScreenState()
    data class SuccessFromCache(val fact: FactModel) : FactScreenState()
    data class Error(val errorModel: ErrorModel) : FactScreenState()
}