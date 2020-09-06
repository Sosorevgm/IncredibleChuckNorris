package com.incredible.chuck.norris.data.screen_state

import com.incredible.chuck.norris.data.models.FactModel

sealed class FactScreenState {
    data class Success(val fact: FactModel) : FactScreenState()
    object Loading : FactScreenState()
    data class Error(val error: String) : FactScreenState()
}