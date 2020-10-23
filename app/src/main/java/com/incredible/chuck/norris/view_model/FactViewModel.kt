package com.incredible.chuck.norris.view_model

import androidx.lifecycle.MutableLiveData
import com.incredible.chuck.norris.data.fact_datasource.FactDataSource
import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.data.screen_state.FactScreenState
import com.sosorevgm.profanityfilter.ProfanityFilter
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FactViewModel(
    private val source: FactDataSource<FactModel>,
    private val profanityFilter: ProfanityFilter
) : BaseViewModel() {

    init {
        profanityFilter.addSwearwords("dickfat")
    }

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
                source.getFact(category)
            }
            val fact = deferredFact.await()
            screenState.value = FactScreenState.Success(applyProfanityFilter(fact))
        }
    }

    fun updateFact(category: String) {
        isProgressbarActive.value = true

        coroutineScope.launch {

            val deferredFact = async {
                source.getFact(category)
            }
            val fact = deferredFact.await()

            screenState.value = FactScreenState.Success(applyProfanityFilter(fact))
            isProgressbarActive.value = false
        }
    }

    fun snackBarUpdateFact(category: String) {
        coroutineScope.launch {
            val deferredFact = async {
                source.getFact(category)
            }
            val fact = deferredFact.await()
            screenState.value = FactScreenState.Success(applyProfanityFilter(fact))
        }
    }

    private fun applyProfanityFilter(fact: FactModel): FactModel {
        val filteredFact = profanityFilter.getFilteredString(fact.fact)
        fact.fact = filteredFact
        return fact
    }

    override fun handleError(error: Throwable) {
        screenState.value = error.message?.let { FactScreenState.Error(it) }
    }
}