package com.incredible.chuck.norris.view_model

import androidx.lifecycle.MutableLiveData
import com.incredible.chuck.norris.data.fact_datasource.FactDataSource
import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.data.screen_state.FactScreenState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FactViewModel(
    private val source: FactDataSource<FactModel>
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
            val deferredFact = async {
                source.getData(category)
            }
            val fact = deferredFact.await()
            screenState.value = FactScreenState.Success(fact)
        }
    }

    fun updateFact(category: String) {
        isProgressbarActive.value = true

        coroutineScope.launch {

            val deferredFact = async {
                source.getData(category)
            }
            val fact = deferredFact.await()
            screenState.value = FactScreenState.Success(fact)
            isProgressbarActive.value = false
        }
    }

    fun snackBarUpdateFact(category: String) {
        coroutineScope.launch {
            val deferredFact = async {
                source.getData(category)
            }
            val fact = deferredFact.await()
            screenState.value = FactScreenState.Success(fact)
        }
    }

    override fun handleError(error: Throwable) {
        screenState.value = error.message?.let { FactScreenState.Error(it) }
    }
}