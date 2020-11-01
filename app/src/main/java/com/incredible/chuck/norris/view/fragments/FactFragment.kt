package com.incredible.chuck.norris.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.data.image_datasource.ImageLoader
import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.data.screen_state.FactScreenState
import com.incredible.chuck.norris.extensions.isNeedToShow
import com.incredible.chuck.norris.utils.getDateString
import com.incredible.chuck.norris.utils.getSnackBarFactError
import com.incredible.chuck.norris.utils.getSnackBarFactsFromCache
import com.incredible.chuck.norris.view_model.FactViewModel
import kotlinx.android.synthetic.main.fact_layout.view.*
import kotlinx.android.synthetic.main.fragment_fact.view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FactFragment : Fragment() {

    companion object {
        const val CATEGORY = "category"
    }

    private val factViewModel: FactViewModel by viewModel()
    private val imageLoader: ImageLoader by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_fact, container, false)
        val category = arguments?.getString(CATEGORY)

        if (factViewModel.currentFact != null) {
            showSuccessStateFromApi(root, category!!, factViewModel.currentFact!!)
        } else {
            category?.let {
                root.tv_fact_category.text = category
                factViewModel.fetchData(it)
            }
        }

        factViewModel.screenState.observe(viewLifecycleOwner, Observer<FactScreenState> {
            when (it) {
                is FactScreenState.Loading -> showLoadingState(root)
                is FactScreenState.SuccessFromApi -> showSuccessStateFromApi(
                    root,
                    category!!,
                    it.fact
                )
                is FactScreenState.SuccessFromCache -> showSuccessStateFromCache(
                    root,
                    category!!,
                    it.fact
                )
                is FactScreenState.ErrorCacheIsEmpty -> showErrorState(root, it.error)
                is FactScreenState.Error -> showErrorState(root, it.error)
            }
        })

        root.layout_fact_fragment_arrow_back.setOnClickListener {
            factViewModel.currentFact = null
            findNavController().popBackStack()
        }

        root.layout_fact_fragment_share.setOnClickListener {
            val fact = root.tv_fact_text.text.toString()
            if (fact.isNotEmpty()) {
                shareFact(fact)
            }
        }

        root.fact_swipe_layout.setOnRefreshListener {
            factViewModel.updateFact(category!!)
            factViewModel.isProgressbarActive.observe(viewLifecycleOwner, Observer<Boolean> {
                when (it) {
                    true -> root.fact_swipe_layout.isRefreshing = true
                    false -> root.fact_swipe_layout.isRefreshing = false
                }
            })
        }
        return root
    }

    private fun showLoadingState(view: View) {
        view.shimmer_fact_layout isNeedToShow true
        view.fact_main_layout isNeedToShow false
        view.layout_fact_fragment_share.isClickable = false
    }

    private fun showSuccessStateFromApi(view: View, category: String, fact: FactModel) {
        view.shimmer_fact_layout isNeedToShow false
        view.fact_main_layout isNeedToShow true
        view.layout_fact_fragment_share.isClickable = true

        val chuckIcon = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.chuck_main_icon
        )

        if (chuckIcon != null) {
            imageLoader.loadImageFromResources(chuckIcon, view.iv_fact_icon)
        } else {
            imageLoader.loadImageFromUrl(fact.iconUrl, view.iv_fact_icon)
        }

        view.tv_fact_category.text = category.capitalize()
        view.tv_fact_text.text = fact.fact
        view.tv_fact_date.text = getDateString(fact.date)
        factViewModel.currentFact = fact
    }

    private fun showSuccessStateFromCache(view: View, category: String, fact: FactModel) {
        view.shimmer_fact_layout isNeedToShow false
        view.fact_main_layout isNeedToShow true
        view.layout_fact_fragment_share.isClickable = true

        val chuckIcon = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.chuck_main_icon
        )

        if (chuckIcon != null) {
            imageLoader.loadImageFromResources(chuckIcon, view.iv_fact_icon)
        } else {
            imageLoader.loadImageFromUrl(fact.iconUrl, view.iv_fact_icon)
        }

        view.tv_fact_category.text = category.capitalize()
        view.tv_fact_text.text = fact.fact
        view.tv_fact_date.text = getDateString(fact.date)
        factViewModel.currentFact = fact
        getSnackBarFactsFromCache(
            requireView(),
            requireContext()
        ).show()
    }

    private fun showErrorState(view: View, error: String) {
        view.shimmer_fact_layout isNeedToShow true
        view.fact_main_layout isNeedToShow false
        view.layout_fact_fragment_share.isClickable = false
        view.fact_swipe_layout.isRefreshing = false
        factViewModel.currentFact = null
        getSnackBarFactError(
            requireView(),
            error,
            requireContext()
        ).show()
    }

    private fun shareFact(fact: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, fact + getString(R.string.google_play_link))
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}