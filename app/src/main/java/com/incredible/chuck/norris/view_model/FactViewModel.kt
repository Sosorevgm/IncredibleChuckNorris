package com.incredible.chuck.norris.view_model

import androidx.lifecycle.MutableLiveData
import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.data.network.NetworkStatus
import com.incredible.chuck.norris.data.repository.FactRepository
import com.incredible.chuck.norris.data.screen_state.FactScreenState
import com.incredible.chuck.norris.exceptions.FactCacheIsEmptyException
import com.sosorevgm.profanityfilter.ProfanityFilter
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FactViewModel(
    private val networkStatus: NetworkStatus,
    private val repository: FactRepository,
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
            if (networkStatus.status.value!!) {
                val deferredFact = async {
                    repository.getFactFromApi(category)
                }
                val fact = deferredFact.await()
                screenState.value = FactScreenState.SuccessFromApi(applyProfanityFilter(fact))
                repository.putFact(fact, category)
            } else {
                val factFromCache = repository.getRandomFactFromCacheByCategory(category)
                screenState.value =
                    FactScreenState.SuccessFromCache(applyProfanityFilter(factFromCache))
            }
        }
    }

    fun updateFact(category: String) {
        screenState.value = FactScreenState.Loading
        isProgressbarActive.value = true

        coroutineScope.launch {
            if (networkStatus.status.value!!) {
                val deferredFact = async {
                    repository.getFactFromApi(category)
                }
                val fact = deferredFact.await()
                screenState.value = FactScreenState.SuccessFromApi(applyProfanityFilter(fact))
                isProgressbarActive.value = false
                repository.putFact(fact, category)
            } else {
                val factFromCache = repository.getRandomFactFromCacheByCategory(category)
                screenState.value =
                    FactScreenState.SuccessFromCache(applyProfanityFilter(factFromCache))
                isProgressbarActive.value = false
            }
        }
    }

    private fun applyProfanityFilter(fact: FactModel): FactModel {
        val filteredFact = profanityFilter.getFilteredString(fact.fact)
        fact.fact = filteredFact
        return fact
    }

    override fun handleError(error: Throwable) {
        if (error is FactCacheIsEmptyException) {
            screenState.value = FactScreenState.ErrorCacheIsEmpty(error.message)
        } else {
            screenState.value = FactScreenState.Error(error.message ?: "Unexpected error")
        }
    }
}