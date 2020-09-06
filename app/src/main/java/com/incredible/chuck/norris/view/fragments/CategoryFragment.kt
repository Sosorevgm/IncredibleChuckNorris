package com.incredible.chuck.norris.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.data.screen_state.CategoryScreenState
import com.incredible.chuck.norris.extensions.hide
import com.incredible.chuck.norris.extensions.show
import com.incredible.chuck.norris.view.adapters.CategoryClickListener
import com.incredible.chuck.norris.view.adapters.CategoryRVAdapter
import com.incredible.chuck.norris.view_model.CategoryViewModel
import kotlinx.android.synthetic.main.category_layout.view.*
import org.koin.android.ext.android.inject

class CategoryFragment : Fragment(), CategoryClickListener {

    companion object {
        const val CATEGORY = "category"
    }

    private val viewModel: CategoryViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_category, container, false)
        viewModel.fetchData()
        viewModel.screenState.observe(viewLifecycleOwner,
            Observer<CategoryScreenState> {
                when (it) {
                    is CategoryScreenState.Loading -> {
                        root.pb_category_fragment.show()
                    }
                    is CategoryScreenState.Success -> {
                        root.pb_category_fragment.hide()
                        root.rv_category_fragment.show()
                        val adapter = CategoryRVAdapter(it.categories, this)
                        root.rv_category_fragment.adapter = adapter
                    }
                    is CategoryScreenState.Error -> {
                        root.pb_category_fragment.hide()
                        root.tv_error_category_fragment.show()
                        root.tv_error_category_fragment.text = it.error
                    }
                }
            })

        root.categories_swipe_layout.setOnRefreshListener {
            viewModel.updateCategories()
            viewModel.isProgressbarActive.observe(viewLifecycleOwner, Observer<Boolean> {
                when (it) {
                    true -> root.categories_swipe_layout.isRefreshing = true
                    false -> root.categories_swipe_layout.isRefreshing = false
                }
            })
        }
        return root
    }

    override fun onFactClick(category: String) {
        findNavController().navigate(R.id.fact_fragment_navigation, Bundle().apply {
            putString(
                CATEGORY, category
            )
        })
    }
}