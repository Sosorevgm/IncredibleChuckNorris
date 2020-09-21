package com.incredible.chuck.norris.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.incredible.chuck.norris.extensions.hide
import com.incredible.chuck.norris.extensions.show
import com.incredible.chuck.norris.utils.getDateString
import com.incredible.chuck.norris.utils.getSnackBarConnectionProblems
import com.incredible.chuck.norris.view_model.FactViewModel
import kotlinx.android.synthetic.main.fact_layout.view.*
import kotlinx.android.synthetic.main.fragment_fact.view.*
import org.koin.android.ext.android.inject

class FactFragment : Fragment() {

    companion object {
        const val CATEGORY = "category"
        const val CURRENT_FACT = "current_fact"
    }

    private val viewModel: FactViewModel by inject()
    private val imageLoader: ImageLoader by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_fact, container, false)
        val category = arguments?.getString(CATEGORY)

        val currentFact: FactModel? = savedInstanceState?.getParcelable(CURRENT_FACT)
        Log.e("myLogs", "cr fact = $currentFact")

        if (currentFact != null) {
            showSuccessState(root, category!!, currentFact)
        } else {
            category?.let {
                root.tv_fact_category.text = category
                viewModel.fetchData(it)
            }
        }

        viewModel.screenState.observe(viewLifecycleOwner, Observer<FactScreenState> {
            when (it) {
                is FactScreenState.Loading -> {
                    showLoadingState(root)
                }
                is FactScreenState.Success -> {
                    showSuccessState(root, category!!, it.fact)
                }
                is FactScreenState.Error -> {
                    showErrorState(root, category!!)
                }
            }
        })

        root.iv_fact_fragment_arrow_back.setOnClickListener {
            findNavController().popBackStack()
        }

        root.iv_fact_fragment_share.setOnClickListener {
            val fact = root.tv_fact_text.text.toString()
            if (fact.isNotEmpty()) {
                shareFact(fact)
            }
        }

        root.fact_swipe_layout.setOnRefreshListener {
            viewModel.updateFact(category!!)
            viewModel.isProgressbarActive.observe(viewLifecycleOwner, Observer<Boolean> {
                when (it) {
                    true -> root.fact_swipe_layout.isRefreshing = true
                    false -> root.fact_swipe_layout.isRefreshing = false
                }
            })
        }
        return root
    }

    private fun showLoadingState(view: View) {
        view.shimmer_fact_layout.show()
        view.fact_main_layout.hide()
        view.iv_fact_fragment_share.isClickable = false
    }

    private fun showSuccessState(view: View, category: String, fact: FactModel) {
        view.shimmer_fact_layout.hide()
        view.fact_main_layout.show()
        view.iv_fact_fragment_share.isClickable = true

        val chuckIcon = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.chuck_main_icon
        )

        if (chuckIcon != null) {
            imageLoader.loadImageFromResources(chuckIcon, view.iv_fact_icon)
        } else {
            imageLoader.loadImageFromUrl(fact.icon_url, view.iv_fact_icon)
        }

        view.tv_fact_category.text = category.capitalize()
        view.tv_fact_text.text = fact.fact
        view.tv_fact_date.text = getDateString(fact.date)
    }

    private fun showErrorState(view: View, category: String) {
        view.shimmer_fact_layout.show()
        view.fact_main_layout.hide()
        view.iv_fact_fragment_share.isClickable = false
        view.fact_swipe_layout.isRefreshing = false
        val snackBar = getSnackBarConnectionProblems(requireView(), requireContext())
        snackBar.setAction("Try again") {
            viewModel.snackBarUpdateFact(category)
        }
        snackBar.show()
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val currentState = viewModel.screenState.value
        if (currentState is FactScreenState.Success) {
            outState.putParcelable(CURRENT_FACT, currentState.fact)
        }
    }
}