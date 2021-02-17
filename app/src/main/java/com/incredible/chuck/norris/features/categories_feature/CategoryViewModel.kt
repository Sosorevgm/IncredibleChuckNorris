package com.incredible.chuck.norris.features.categories_feature

import androidx.lifecycle.MutableLiveData
import com.incredible.chuck.norris.common.BaseViewModel
import com.incredible.chuck.norris.common.ErrorModel
import com.incredible.chuck.norris.features.categories_feature.data_source.CategoriesRepository
import com.incredible.chuck.norris.navigation.Screens
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val repository: CategoriesRepository,
    private val router: Router
) : BaseViewModel() {

    val screenState: MutableLiveData<CategoryScreenState> by lazy {
        MutableLiveData<CategoryScreenState>()
    }

    var currentCategories: List<String>? = null

    fun fetchData() {
        screenState.value = CategoryScreenState.Loading

        coroutineScope.launch {
            val newScreenState = repository.getCategories()
            screenState.postValue(newScreenState)
            if (newScreenState is CategoryScreenState.SuccessFromApi) {
                currentCategories = newScreenState.categories
            } else if (newScreenState is CategoryScreenState.SuccessFromCache) {
                currentCategories = newScreenState.categories
            }
        }
    }

    fun factClicked(category: String) {
        router.navigateTo(Screens.FactScreen(category))
    }

    override fun handleError(error: Throwable) {
        screenState.value =
            CategoryScreenState.Error(ErrorModel(title = error.message ?: "Unexpected error"))
    }
}