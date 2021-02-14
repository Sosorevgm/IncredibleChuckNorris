package com.incredible.chuck.norris.features.main

import com.incredible.chuck.norris.common.BaseViewModel
import com.incredible.chuck.norris.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainViewModel @Inject constructor(
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