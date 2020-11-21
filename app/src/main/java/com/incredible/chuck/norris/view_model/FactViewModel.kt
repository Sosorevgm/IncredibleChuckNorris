package com.incredible.chuck.norris.view_model

import androidx.lifecycle.MutableLiveData
import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.data.repository.FactRepository
import com.incredible.chuck.norris.data.screen_state.FactScreenState
import kotlinx.coroutines.launch

class FactViewModel(
    private val repository: FactRepository
) : BaseViewModel() {


    val screenState: MutableLiveData<FactScreenState> by lazy {
        MutableLiveData<FactScreenState>()
    }

    val isProgressbarActive: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    var currentFact: FactModel? = null

    fun fetchData(category: String) {
        screenState.value = FactScreenState.Loading
        coroutineScope.launch {
            val newScreenState = repository.getFact(category)
            screenState.value = newScreenState
            if (newScreenState is FactScreenState.SuccessFromApi) {
                currentFact = newScreenState.fact
            } else if (newScreenState is FactScreenState.SuccessFromCache) {
                currentFact = newScreenState.fact
            }
        }
    }

    fun updateFact(category: String) {
        screenState.value = FactScreenState.Loading
        isProgressbarActive.value = true

        coroutineScope.launch {
            val newScreenState = repository.getFact(category)
            screenState.value = newScreenState
            isProgressbarActive.value = false
            if (newScreenState is FactScreenState.SuccessFromApi) {
                currentFact = newScreenState.fact
            } else if (newScreenState is FactScreenState.SuccessFromCache) {
                currentFact = newScreenState.fact
            }
        }
    }

    override fun handleError(error: Throwable) {
        screenState.value = FactScreenState.Error(error.message ?: "Unexpected error")
    }
}