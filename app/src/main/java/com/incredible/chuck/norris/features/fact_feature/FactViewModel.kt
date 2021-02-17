package com.incredible.chuck.norris.features.fact_feature

import androidx.lifecycle.MutableLiveData
import com.incredible.chuck.norris.common.BaseViewModel
import com.incredible.chuck.norris.common.ErrorModel
import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.features.fact_feature.data_source.FactRepository
import com.incredible.chuck.norris.navigation.Screens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class FactViewModel @Inject constructor(
    private val repository: FactRepository,
    private val router: Router
) : BaseViewModel() {

    companion object {
        private const val FACT_DELAY = 500L
    }

    val screenState: MutableLiveData<FactScreenState> by lazy {
        MutableLiveData<FactScreenState>()
    }

    var currentCategory: String = ""
    var currentFact: FactModel? = null

    fun fetchData(category: String) {
        currentCategory = category
        screenState.value = FactScreenState.Loading
        coroutineScope.launch {
            delay(FACT_DELAY)
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
        currentCategory = category
        screenState.value = FactScreenState.Loading

        coroutineScope.launch {
            delay(FACT_DELAY)
            val newScreenState = repository.getFact(category)
            screenState.value = newScreenState
            if (newScreenState is FactScreenState.SuccessFromApi) {
                currentFact = newScreenState.fact
            } else if (newScreenState is FactScreenState.SuccessFromCache) {
                currentFact = newScreenState.fact
            }
        }
    }

    fun shareFact(shareMessage: String) {
        router.navigateTo(Screens.ShareScreen(shareMessage))
    }

    override fun handleError(error: Throwable) {
        screenState.value =
            FactScreenState.Error(ErrorModel(message = error.message ?: "Unexpected error"))
    }
}