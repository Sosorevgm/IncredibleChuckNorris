package com.incredible.chuck.norris.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.data.image_datasource.ImageLoader
import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.data.screen_state.FactScreenState
import com.incredible.chuck.norris.databinding.FragmentFactBinding
import com.incredible.chuck.norris.extensions.isNeedToShow
import com.incredible.chuck.norris.utils.Constants.CATEGORY
import com.incredible.chuck.norris.utils.getDateString
import com.incredible.chuck.norris.utils.getSnackBarFactsFromCache
import com.incredible.chuck.norris.view_model.FactViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.terrakok.cicerone.Router

class FactFragment : Fragment() {

    companion object {
        fun getInstance(category: String) = FactFragment().apply {
            arguments = Bundle().apply {
                putString(CATEGORY, category)
            }
        }
    }

    private val viewModel: FactViewModel by viewModel()
    private val imageLoader: ImageLoader by inject()
    private val router: Router by inject()

    private var _binding: FragmentFactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFactBinding.inflate(inflater, container, false)

        val category = arguments?.getString(CATEGORY)

        if (viewModel.currentFact != null) {
            showSuccessState(category!!, viewModel.currentFact!!)
        } else {
            category?.let {
                binding.factLayoutId.tvFactCategory.text = category
                viewModel.fetchData(it)
            }
        }

        viewModel.screenState.observe(viewLifecycleOwner, {
            when (it) {
                is FactScreenState.Loading -> showLoadingState()
                is FactScreenState.SuccessFromApi -> showSuccessState(category!!, it.fact)
                is FactScreenState.SuccessFromCache -> {
                    showSuccessState(category!!, it.fact)
                    getSnackBarFactsFromCache(requireView(), requireContext()).show()
                }
                is FactScreenState.ErrorCacheIsEmpty -> showErrorState(it.error)
                is FactScreenState.Error -> showErrorState(it.error)
            }
        })

        binding.layoutFactFragmentArrowBack.setOnClickListener {
            router.exit()
        }

        binding.layoutFactFragmentShare.setOnClickListener {
            val fact = binding.factLayoutId.tvFactText.text.toString()
            if (fact.isNotEmpty()) {
                val message = "$fact ${getString(R.string.google_play_link)}"
                viewModel.shareFact(message)
            }
        }

        binding.factLayoutId.factSwipeLayout.setOnRefreshListener {
            binding.factLayoutId.factSwipeLayout.isRefreshing = false
            viewModel.updateFact(category!!)
        }
        return binding.root
    }

    private fun showLoadingState() {
        binding.factLayoutId.shimmerFactLayout isNeedToShow true
        binding.factLayoutId.factMainLayout isNeedToShow false
        binding.factLayoutId.factErrorLayout isNeedToShow false
        binding.layoutFactFragmentShare.isClickable = false
    }

    private fun showSuccessState(category: String, fact: FactModel) {
        binding.factLayoutId.shimmerFactLayout isNeedToShow false
        binding.factLayoutId.factMainLayout isNeedToShow true
        binding.factLayoutId.factErrorLayout isNeedToShow false
        binding.layoutFactFragmentShare.isClickable = true

        val chuckIcon = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.chuck_main_icon
        )

        if (chuckIcon != null) {
            imageLoader.loadImageFromResources(chuckIcon, binding.factLayoutId.ivFactIcon)
        } else {
            imageLoader.loadImageFromUrl(fact.iconUrl, binding.factLayoutId.ivFactIcon)
        }

        binding.factLayoutId.tvFactCategory.text = category.capitalize()
        binding.factLayoutId.tvFactText.text = fact.fact
        binding.factLayoutId.tvFactDate.text = getDateString(fact.date)
        viewModel.currentFact = fact
    }

    private fun showErrorState(error: String) {
        binding.factLayoutId.shimmerFactLayout isNeedToShow false
        binding.factLayoutId.factMainLayout isNeedToShow false
        binding.factLayoutId.factErrorLayout isNeedToShow true
        binding.layoutFactFragmentShare.isClickable = false
        binding.factLayoutId.factSwipeLayout.isRefreshing = false
        viewModel.currentFact = null
        binding.factLayoutId.tvFactError.text = error
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}