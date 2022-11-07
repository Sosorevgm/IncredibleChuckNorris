package com.incredible.chuck.norris.onboarding.internal

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    companion object {
        private const val ITEMS_COUNT = 3
    }

    override fun getItemCount() = ITEMS_COUNT

    override fun createFragment(position: Int) = OnboardingFragment.getInstance(position)
}
