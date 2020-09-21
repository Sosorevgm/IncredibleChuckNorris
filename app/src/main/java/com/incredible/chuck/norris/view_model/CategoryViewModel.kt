package com.incredible.chuck.norris.view_model

import androidx.lifecycle.MutableLiveData
import com.incredible.chuck.norris.data.category_datasource.CategoryDataSource
import com.incredible.chuck.norris.data.screen_state.CategoryScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject

class CategoryViewModel(
    private val source: CategoryDataSource<List<String>>
) : BaseViewModel() {

    val screenState: MutableLiveData<CategoryScreenState> by lazy {
        MutableLiveData<CategoryScreenState>()
    }

    init {
        screenState.value = CategoryScreenState.Loading

        coroutineScope.launch {
            val categories = withContext(Dispatchers.IO) {
                source.getData()
            }
            if (categories.isNotEmpty()) {
                screenState.value = CategoryScreenState.Success(categories)
            }
        }
    }

    fun updateCategories() {
        screenState.value = CategoryScreenState.Loading

        coroutineScope.launch {
            val categories = withContext(Dispatchers.IO) {
                source.getData()
            }
            if (categories.isNotEmpty()) {
                screenState.value = CategoryScreenState.Success(categories)
            }
        }
    }

    override fun handleError(error: Throwable) {
        screenState.value = CategoryScreenState.Error
    }
}