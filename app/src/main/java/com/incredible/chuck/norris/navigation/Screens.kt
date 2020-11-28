package com.incredible.chuck.norris.navigation

import android.content.Context
import android.content.Intent
import com.incredible.chuck.norris.view.activities.MainActivity
import com.incredible.chuck.norris.view.activities.OnboardingActivity
import com.incredible.chuck.norris.view.fragments.CategoryFragment
import com.incredible.chuck.norris.view.fragments.FactFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class OnboardingScreen() : SupportAppScreen() {
        override fun getActivityIntent(context: Context?) =
            Intent(context, OnboardingActivity::class.java)
    }

    class MainScreen() : SupportAppScreen() {
        override fun getActivityIntent(context: Context?) =
            Intent(context, MainActivity::class.java)
    }

    class ShareScreen(val text: String) : SupportAppScreen() {
        override fun getActivityIntent(context: Context?): Intent {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, text)
            intent.type = "text/plain"
            return Intent.createChooser(intent, null)
        }
    }

    class CategoriesScreen() : SupportAppScreen() {
        override fun getFragment() = CategoryFragment.getInstance()
    }

    class FactScreen(val category: String) : SupportAppScreen() {
        override fun getFragment() = FactFragment.getInstance(category)
    }
}

