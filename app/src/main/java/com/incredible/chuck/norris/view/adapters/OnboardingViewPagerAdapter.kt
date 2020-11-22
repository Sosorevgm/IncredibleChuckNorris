package com.incredible.chuck.norris.view.adapters

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.incredible.chuck.norris.view.fragments.OnboardingFragment

class OnboardingViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int) = OnboardingFragment.getInstance(position)
}