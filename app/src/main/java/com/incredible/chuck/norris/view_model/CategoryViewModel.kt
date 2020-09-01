package com.incredible.chuck.norris.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.incredible.chuck.norris.model.CategoryScreenState
import com.incredible.chuck.norris.model.datasource.CategoryRetrofitImplementation
import com.incredible.chuck.norris.model.datasource.DataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private lateinit var source: DataSource<List<String>>
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    val screenState: MutableLiveData<CategoryScreenState> by lazy {
        MutableLiveData<CategoryScreenState>()
    }

    fun updateData() {
        screenState.value = CategoryScreenState.Loading

        source = CategoryRetrofitImplementation()

        scope.launch {
            val categories = source.getData()
            if (categories.isNotEmpty()){
                screenState.value = CategoryScreenState.Success(categories)
            } else {
                screenState.value = CategoryScreenState.Error("Connection problem")
            }
        }
    }
}