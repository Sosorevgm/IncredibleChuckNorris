package com.incredible.chuck.norris.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.databinding.ActivityOnboardingBinding
import com.incredible.chuck.norris.extensions.isNeedToShow
import com.incredible.chuck.norris.view.adapters.OnboardingViewPagerAdapter
import com.incredible.chuck.norris.view.fragments.OnboardingFirstFragment
import com.incredible.chuck.norris.view.fragments.OnboardingSecondFragment
import com.incredible.chuck.norris.view.fragments.OnboardingThirdFragment

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragments = listOf(
            OnboardingFirstFragment(),
            OnboardingSecondFragment(),
            OnboardingThirdFragment()
        )

        val adapter = OnboardingViewPagerAdapter(fragments, supportFragmentManager, lifecycle)
        binding.onboardingViewPager.adapter = adapter

        TabLayoutMediator(binding.onboardintTabsLayout, binding.onboardingViewPager) { tab, _ ->
            tab.icon = getDrawable(R.drawable.onboarding_tab_layout_dot)
            tab.icon?.setTint(getColor(R.color.silver))
        }.attach()

        binding.onboardintTabsLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(getColor(R.color.white))
                when (tab?.position) {
                    0 -> {
                        binding.onboardingLayoutPrevious isNeedToShow false
                        binding.onboardingTvNext.text = getString(R.string.onboarding_next)
                    }
                    1 -> {
                        binding.onboardingLayoutPrevious isNeedToShow true
                        binding.onboardingTvNext.text = getString(R.string.onboarding_next)
                    }
                    2 -> {
                        binding.onboardingLayoutPrevious isNeedToShow true
                        binding.onboardingTvNext.text = getString(R.string.onboarding_got_it)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(getColor(R.color.silver))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.onboardintTabsLayout.getTabAt(0)?.icon?.setTint(getColor(R.color.white))

        binding.onboardingLayoutPrevious.setOnClickListener {
            binding.onboardingViewPager.setCurrentItem(
                binding.onboardingViewPager.currentItem - 1,
                true
            )
        }

        binding.onboardingLayoutNext.setOnClickListener {
            if (binding.onboardingViewPager.currentItem < 2) {
                binding.onboardingViewPager.setCurrentItem(
                    binding.onboardingViewPager.currentItem + 1,
                    true
                )
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