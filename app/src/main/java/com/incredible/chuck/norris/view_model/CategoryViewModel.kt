package com.incredible.chuck.norris.view_model

import androidx.lifecycle.MutableLiveData
import com.incredible.chuck.norris.data.repository.CategoriesRepository
import com.incredible.chuck.norris.data.screen_state.CategoryScreenState
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val repository: CategoriesRepository
) : BaseViewModel() {

    val screenState: MutableLiveData<CategoryScreenState> by lazy {
        MutableLiveData<CategoryScreenState>()
    }

    var currentCategories: List<String>? = null

    fun fetchData() {
        screenState.value = CategoryScreenState.Loading

        coroutineScope.launch {
            val newScreenState = repository.getCategories()
            screenState.value = newScreenState
            if (newScreenState is CategoryScreenState.SuccessFromApi) {
                currentCategories = newScreenState.categories
            } else if (newScreenState is CategoryScreenState.SuccessFromCache) {
                currentCategories = newScreenState.categories
            }
        }
    }

    override fun handleError(error: Throwable) {
        screenState.value = CategoryScreenState.Error(error.message ?: "Unexpected error")
    }
}