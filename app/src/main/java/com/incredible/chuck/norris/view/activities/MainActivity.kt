package com.incredible.chuck.norris.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.navigation.Screens
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity() {

    private val router: Router by inject()
    private val navigatorHolder: NavigatorHolder by inject()
    private lateinit var navigator: SupportAppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator = SupportAppNavigator(this, R.id.main_fragment_container)
        router.newRootScreen(Screens.CategoriesScreen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}