package com.incredible.chuck.norris.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.incredible.chuck.norris.R
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

class CustomAppNavigator(
    fragmentActivity: FragmentActivity,
    containerId: Int
) : SupportAppNavigator(fragmentActivity, containerId) {


    override fun setupFragmentTransaction(
        command: Command?,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction?
    ) {

        if (command is Forward){
            fragmentTransaction?.setCustomAnimations(
                R.anim.fragment_slide_in,
                R.anim.fragment_fade_out,
                R.anim.fragment_fade_in,
                R.anim.fragment_slide_out
            )
        }

        super.setupFragmentTransaction(
            command,
            currentFragment,
            nextFragment,
            fragmentTransaction
        )
    }

}