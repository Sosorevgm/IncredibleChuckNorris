package com.incredible.chuck.norris.view_model

import androidx.lifecycle.MutableLiveData
import com.incredible.chuck.norris.data.category_datasource.CategoryDataSource
import com.incredible.chuck.norris.data.screen_state.CategoryScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject

class CategoryViewModel : BaseViewModel() {

    private val source: CategoryDataSource<List<String>> by inject()

    val screenState: MutableLiveData<CategoryScreenState> by lazy {
        MutableLiveData<CategoryScreenState>()
    }

    val isProgressbarActive: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
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
        isProgressbarActive.value = true

        coroutineScope.launch {
            val categories = withContext(Dispatchers.IO) {
                source.getData()
            }
            if (categories.isNotEmpty()) {
                screenState.value = CategoryScreenState.Success(categories)
            }
            isProgressbarActive.value = false
        }

    }

    override fun handleError(error: Throwable) {
        screenState.value = error.localizedMessage?.let { CategoryScreenState.Error(it) }
    }
}