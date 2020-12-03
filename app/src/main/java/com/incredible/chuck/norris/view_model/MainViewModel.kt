package com.incredible.chuck.norris.view_model

import com.incredible.chuck.norris.navigation.Screens
import ru.terrakok.cicerone.Router

class MainViewModel(
    private val router: Router
) : BaseViewModel() {

    fun setRootScreen() {
        router.newRootScreen(Screens.CategoriesScreen())
    }

    override fun handleError(error: Throwable) {

    }
}