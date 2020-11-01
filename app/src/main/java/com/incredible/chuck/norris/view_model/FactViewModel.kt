package com.incredible.chuck.norris.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.data.network.NetworkStatus
import com.incredible.chuck.norris.data.repository.FactRepository
import com.incredible.chuck.norris.data.screen_state.FactScreenState
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
            val deferredFact = async {
                repository.getFactFromApi(category)
            }
            val fact = deferredFact.await()
            screenState.value = FactScreenState.SuccessFromApi(applyProfanityFilter(fact))
        }
    }

    fun updateFact(category: String) {
        isProgressbarActive.value = true

        coroutineScope.launch {

            val deferredFact = async {
                repository.getFactFromApi(category)
            }
            val fact = deferredFact.await()

            screenState.value = FactScreenState.SuccessFromApi(applyProfanityFilter(fact))
            isProgressbarActive.value = false
        }
    }

    fun snackBarUpdateFact(category: String) {
        coroutineScope.launch {
            val deferredFact = async {
                repository.getFactFromApi(category)
            }
            val fact = deferredFact.await()
            screenState.value = FactScreenState.SuccessFromApi(applyProfanityFilter(fact))
        }
    }

    private fun applyProfanityFilter(fact: FactModel): FactModel {
        val filteredFact = profanityFilter.getFilteredString(fact.fact)
        fact.fact = filteredFact
        return fact
    }

    override fun handleError(error: Throwable) {
        Log.e("myLogs", "error = ${error.message}")
        screenState.value = error.message?.let { FactScreenState.Error(it) }
    }
}