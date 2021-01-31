package com.incredible.chuck.norris.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.navigation.CustomAppNavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val navigatorHolder: NavigatorHolder by inject()
    private lateinit var navigator: SupportAppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator = CustomAppNavigator(this, R.id.main_fragment_container)
        viewModel.setRootScreen()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}