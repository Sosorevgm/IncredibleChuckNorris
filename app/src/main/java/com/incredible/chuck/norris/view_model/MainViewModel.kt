package com.incredible.chuck.norris.view_model

import com.incredible.chuck.norris.navigation.Screens
import ru.terrakok.cicerone.Router

class MainViewModel(
    private val router: Router
) : BaseViewModel() {

    private var rootScreenInstalled = false

    fun setRootScreen() {
        if (!rootScreenInstalled) {
            router.newRootScreen(Screens.CategoriesScreen())
            rootScreenInstalled = true
        }
    }

    override fun handleError(error: Throwable) {

    }
}