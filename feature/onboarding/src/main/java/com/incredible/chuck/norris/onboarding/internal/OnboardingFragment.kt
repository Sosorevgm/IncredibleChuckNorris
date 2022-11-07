package com.incredible.chuck.norris.onboarding.internal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.incredible.chuck.norris.onboarding.R
import com.incredible.chuck.norris.onboarding.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    companion object {
        private const val ONBOARDING_FRAGMENT_NUMBER = "onboarding.fragment.number"
        fun getInstance(fragmentNumber: Int) = OnboardingFragment().apply {
            arguments = Bundle().apply {
                putInt(ONBOARDING_FRAGMENT_NUMBER, fragmentNumber)
            }
        }
    }

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)

        arguments?.getInt(ONBOARDING_FRAGMENT_NUMBER)?.let { fragmentNumber ->
            when (fragmentNumber) {
                0 -> binding.ivOnboarding.setImageResource(R.drawable.onboarding_screen_one)
                1 -> binding.ivOnboarding.setImageResource(R.drawable.onboarding_screen_two)
                2 -> binding.ivOnboarding.setImageResource(R.drawable.onboarding_screen_three)
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
