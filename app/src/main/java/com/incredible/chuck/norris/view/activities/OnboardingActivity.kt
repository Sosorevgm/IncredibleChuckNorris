package com.incredible.chuck.norris.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.extensions.isNeedToShow
import com.incredible.chuck.norris.view.adapters.OnboardingViewPagerAdapter
import com.incredible.chuck.norris.view.fragments.OnboardingFirstFragment
import com.incredible.chuck.norris.view.fragments.OnboardingSecondFragment
import com.incredible.chuck.norris.view.fragments.OnboardingThirdFragment
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val fragments = listOf(
            OnboardingFirstFragment(),
            OnboardingSecondFragment(),
            OnboardingThirdFragment()
        )

        val adapter = OnboardingViewPagerAdapter(fragments, supportFragmentManager, lifecycle)
        onboarding_view_pager.adapter = adapter

        TabLayoutMediator(onboardint_tabs_layout, onboarding_view_pager) { tab, _ ->
            tab.icon = getDrawable(R.drawable.onboarding_tab_layout_dot)
            tab.icon?.setTint(getColor(R.color.silver))
        }.attach()

        onboardint_tabs_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(getColor(R.color.white))
                when (tab?.position) {
                    0 -> {
                        onboarding_layout_previous isNeedToShow false
                        onboarding_tv_next.text = getString(R.string.onboarding_next)
                    }
                    1 -> {
                        onboarding_layout_previous isNeedToShow true
                        onboarding_tv_next.text = getString(R.string.onboarding_next)
                    }
                    2 -> {
                        onboarding_layout_previous isNeedToShow true
                        onboarding_tv_next.text = getString(R.string.onboarding_got_it)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(getColor(R.color.silver))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        onboardint_tabs_layout.getTabAt(0)?.icon?.setTint(getColor(R.color.white))

        onboarding_layout_previous.setOnClickListener {
            onboarding_view_pager.setCurrentItem(onboarding_view_pager.currentItem - 1, true)
        }

        onboarding_layout_next.setOnClickListener {
            if (onboarding_view_pager.currentItem < 2) {
                onboarding_view_pager.setCurrentItem(onboarding_view_pager.currentItem + 1, true)
            } else {
                val sharedPreferences = getSharedPreferences(
                    getString(R.string.onboarding_preference_key),
                    Context.MODE_PRIVATE
                )

                with(sharedPreferences.edit()) {
                    putBoolean(getString(R.string.onboarding_flag_key), true)
                    apply()
                }

                val mainIntent = Intent(this, MainActivity::class.java)
                startActivity(mainIntent)
                finish()
            }
        }
    }
}