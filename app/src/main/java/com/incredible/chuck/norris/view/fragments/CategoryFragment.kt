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
import com.incredible.chuck.norris.extensions.isNeedToShow
import com.incredible.chuck.norris.utils.getSnackBarCacheData
import com.incredible.chuck.norris.utils.getSnackBarConnectionProblems
import com.incredible.chuck.norris.view.adapters.CategoryClickListener
import com.incredible.chuck.norris.view.adapters.CategoryRVAdapter
import com.incredible.chuck.norris.view_model.CategoryViewModel
import kotlinx.android.synthetic.main.category_layout.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : Fragment(), CategoryClickListener {

    companion object {
        const val CATEGORY = "category"
    }

    private val categoryViewModel: CategoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_category, container, false)
        val adapter = CategoryRVAdapter(listOf(), this)
        root.rv_category_fragment.adapter = adapter

        if (categoryViewModel.currentCategories != null) {
            showSuccessState(root, adapter, categoryViewModel.currentCategories!!)
        } else {
            categoryViewModel.fetchData()
        }

        categoryViewModel.screenState.observe(viewLifecycleOwner,
            Observer<CategoryScreenState> {
                when (it) {
                    is CategoryScreenState.Loading -> showLoadingState(root)
                    is CategoryScreenState.SuccessFromApi -> showSuccessState(
                        root,
                        adapter,
                        it.categories
                    )
                    is CategoryScreenState.SuccessFromCache -> {
                        showSuccessStateFromCache(root, adapter, it.categories)
                    }
                    is CategoryScreenState.Error -> showErrorState(root)
                }
            })

        root.categories_swipe_layout.setOnRefreshListener {
            root.categories_swipe_layout.isRefreshing = false
            categoryViewModel.fetchData()
            root.rv_category_fragment.scheduleLayoutAnimation()
        }
        return root
    }

    private fun showLoadingState(view: View) {
        view.rv_category_fragment isNeedToShow false
        view.iv_category_error isNeedToShow false
        view.pb_category_fragment isNeedToShow true
        stopAnimation(view.iv_category_error)
    }

    private fun showSuccessState(view: View, adapter: CategoryRVAdapter, categories: List<String>) {
        view.pb_category_fragment isNeedToShow false
        view.iv_category_error isNeedToShow false
        view.rv_category_fragment isNeedToShow true
        stopAnimation(view.iv_category_error)
        adapter.updateCategoryList(categories)
        categoryViewModel.currentCategories = categories
    }

    private fun showSuccessStateFromCache(
        view: View,
        adapter: CategoryRVAdapter,
        categories: List<String>
    ) {
        view.pb_category_fragment isNeedToShow false
        view.iv_category_error isNeedToShow false
        view.rv_category_fragment isNeedToShow true
        stopAnimation(view.iv_category_error)
        adapter.updateCategoryList(categories)
        categoryViewModel.currentCategories = categories
        getSnackBarCacheData(
            requireView(),
            requireContext()
        ).setAction(getString(R.string.try_again)) {
            categoryViewModel.fetchData()
            view.rv_category_fragment.scheduleLayoutAnimation()
        }.show()
    }

    private fun showErrorState(view: View) {
        view.pb_category_fragment isNeedToShow false
        view.rv_category_fragment isNeedToShow false
        view.iv_category_error isNeedToShow true
        startAnimation(view.iv_category_error)
        getSnackBarConnectionProblems(
            requireView(),
            requireContext()
        ).setAction(getString(R.string.try_again)) {
            categoryViewModel.fetchData()
            view.rv_category_fragment.scheduleLayoutAnimation()
        }.show()
    }

    private fun startAnimation(view: View) {
        val animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.chuck_exception_icon_rotate)
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