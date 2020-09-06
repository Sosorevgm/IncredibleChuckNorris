package com.incredible.chuck.norris.view_model

import androidx.lifecycle.MutableLiveData
import com.incredible.chuck.norris.data.category_datasource.CategoryDataSource
import com.incredible.chuck.norris.data.category_datasource.CategoryRetrofitImplementation
import com.incredible.chuck.norris.data.screen_state.CategoryScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel : BaseViewModel() {

    private lateinit var source: CategoryDataSource<List<String>>

    val screenState: MutableLiveData<CategoryScreenState> by lazy {
        MutableLiveData<CategoryScreenState>()
    }

    val isProgressbarActive: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun fetchData() {
        screenState.value = CategoryScreenState.Loading

        source =
            CategoryRetrofitImplementation()

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

        source = CategoryRetrofitImplementation()

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