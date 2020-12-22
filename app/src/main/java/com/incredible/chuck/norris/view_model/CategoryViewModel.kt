package com.incredible.chuck.norris.view_model

import androidx.lifecycle.MutableLiveData
import com.incredible.chuck.norris.data.repository.CategoriesRepository
import com.incredible.chuck.norris.data.screen_state.CategoryScreenState
import com.incredible.chuck.norris.data.view.ErrorModel
import com.incredible.chuck.norris.navigation.Screens
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router

class CategoryViewModel(
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