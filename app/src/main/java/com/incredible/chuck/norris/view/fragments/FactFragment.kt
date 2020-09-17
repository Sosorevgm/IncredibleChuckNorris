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
    }

    private val viewModel: FactViewModel by inject()
    private val imageLoader: ImageLoader by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_fact, container, false)
        val category = arguments?.getString(CATEGORY)

        category?.let {
            root.tv_fact_category.text = category.capitalize()
            viewModel.fetchData(it)
        }
        viewModel.screenState.observe(viewLifecycleOwner, Observer<FactScreenState> {
            when (it) {
                is FactScreenState.Loading -> {
                    root.shimmer_fact_layout.show()
                    root.fact_main_layout.hide()
                }
                is FactScreenState.Success -> {
                    root.shimmer_fact_layout.hide()
                    root.fact_main_layout.show()

                    val chuckIcon = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.chuck_main_icon
                    )

                    if (chuckIcon != null) {
                        imageLoader.loadImageFromResources(chuckIcon, root.iv_fact_icon)
                    } else {
                        imageLoader.loadImageFromUrl(it.fact.icon_url, root.iv_fact_icon)
                    }

                    root.tv_fact_text.text = it.fact.fact
                    root.tv_fact_date.text = getDateString(it.fact.date)
                }
                is FactScreenState.Error -> {
                    root.shimmer_fact_layout.show()
                    root.fact_main_layout.hide()
                    root.fact_swipe_layout.isRefreshing = false
                    val snackBar = getSnackBarConnectionProblems(requireView(), requireContext())
                    snackBar.setAction("Try again") {
                        viewModel.snackBarUpdateFact(category!!)
                    }
                    snackBar.show()
                }
            }
        })

        root.iv_fact_fragment_arrow_back.setOnClickListener {
            findNavController().popBackStack()
        }

        root.iv_fact_fragment_share.setOnClickListener {
            val fact = root.tv_fact_text.text.toString()
            if (fact.isNotEmpty()){
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

    private fun shareFact(fact: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, fact)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}