package com.incredible.chuck.norris.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.incredible.chuck.norris.data.network.NetworkStatus
import com.incredible.chuck.norris.data.repository.CategoriesRepository
import com.incredible.chuck.norris.data.screen_state.CategoryScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel(
    private val network: NetworkStatus,
    private val repository: CategoriesRepository
) : BaseViewModel() {

    val screenState: MutableLiveData<CategoryScreenState> by lazy {
        MutableLiveData<CategoryScreenState>()
    }

    var currentCategories: List<String>? = null

    fun fetchData() {
        screenState.value = CategoryScreenState.Loading

        coroutineScope.launch {
            if (network.status.value!!) {

                val categoriesFromApi = withContext(Dispatchers.IO) {
                    repository.getCategoriesFromApi()
                }

                if (categoriesFromApi.isNotEmpty()) {
                    screenState.value = CategoryScreenState.SuccessFromApi(categoriesFromApi)
                    repository.putCategoriesInCache(categoriesFromApi)
                }
            } else {

                val categoriesFromCache = withContext(Dispatchers.IO) {
                    repository.getCategoriesFromCache()
                }

                if (categoriesFromCache.isNotEmpty()) {
                    screenState.value = CategoryScreenState.SuccessFromCache(categoriesFromCache)
                } else {
                    screenState.value = CategoryScreenState.Error
                }
            }
        }
    }

    override fun handleError(error: Throwable) {
        screenState.value = CategoryScreenState.Error
        Log.e("myLogs", "error = ${error.javaClass}")
    }
}