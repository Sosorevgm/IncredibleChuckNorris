package com.incredible.chuck.norris.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.data.screen_state.CategoryScreenState
import com.incredible.chuck.norris.extensions.hide
import com.incredible.chuck.norris.extensions.show
import com.incredible.chuck.norris.utils.getSnackBarConnectionProblems
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
        val adapter = CategoryRVAdapter(listOf(), this)
        root.rv_category_fragment.adapter = adapter

        viewModel.screenState.observe(viewLifecycleOwner,
            Observer<CategoryScreenState> {
                when (it) {
                    is CategoryScreenState.Loading -> {
                        root.rv_category_fragment.hide()
                        root.iv_category_error.hide()
                        stopAnimation(root.iv_category_error)
                        root.pb_category_fragment.show()
                    }
                    is CategoryScreenState.Success -> {
                        root.pb_category_fragment.hide()
                        root.iv_category_error.hide()
                        stopAnimation(root.iv_category_error)
                        root.rv_category_fragment.show()
                        adapter.updateCategoryList(it.categories)
                    }
                    is CategoryScreenState.Error -> {
                        root.pb_category_fragment.hide()
                        root.rv_category_fragment.hide()
                        root.iv_category_error.show()
                        startAnimation(root.iv_category_error)
                        val snackBar =
                            getSnackBarConnectionProblems(requireView(), requireContext())
                        snackBar.setAction("Try again") {
                            viewModel.updateCategories()
                            root.rv_category_fragment.scheduleLayoutAnimation()
                        }
                        snackBar.show()
                    }
                }
            })

        root.categories_swipe_layout.setOnRefreshListener {
            root.categories_swipe_layout.isRefreshing = false
            viewModel.updateCategories()
            root.rv_category_fragment.scheduleLayoutAnimation()
        }
        return root
    }

    private fun startAnimation(view: View) {
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.chuck_rotate)
        view.iv_category_error.startAnimation(animation)
    }

    private fun stopAnimation(view: View) {
        view.iv_category_error.animation = null
    }

    override fun onFactClick(category: String) {
        findNavController().navigate(R.id.fact_fragment_navigation, Bundle().apply {
            putString(
                CATEGORY, category
            )
        })
    }
}