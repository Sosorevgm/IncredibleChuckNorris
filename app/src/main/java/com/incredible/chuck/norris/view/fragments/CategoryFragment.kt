package com.incredible.chuck.norris.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.data.screen_state.CategoryScreenState
import com.incredible.chuck.norris.databinding.FragmentCategoryBinding
import com.incredible.chuck.norris.extensions.isNeedToShow
import com.incredible.chuck.norris.utils.getSnackBarCategoriesFromCache
import com.incredible.chuck.norris.view.adapters.CategoryClickListener
import com.incredible.chuck.norris.view.adapters.CategoryRVAdapter
import com.incredible.chuck.norris.view_model.CategoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : Fragment(), CategoryClickListener {

    companion object {
        const val CATEGORY = "category"
    }

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private val categoryViewModel: CategoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)

        val adapter = CategoryRVAdapter(listOf(), this)
        binding.categoryLayoutId.rvCategoryFragment.adapter = adapter

        if (categoryViewModel.currentCategories != null) {
            showSuccessStateFromApi(adapter, categoryViewModel.currentCategories!!)
        } else {
            categoryViewModel.fetchData()
        }

        categoryViewModel.screenState.observe(viewLifecycleOwner,
            {
                when (it) {
                    is CategoryScreenState.Loading -> showLoadingState()
                    is CategoryScreenState.SuccessFromApi -> showSuccessStateFromApi(
                        adapter,
                        it.categories
                    )
                    is CategoryScreenState.SuccessFromCache -> {
                        showSuccessStateFromCache(adapter, it.categories)
                    }
                    is CategoryScreenState.ErrorCacheIsEmpty -> showErrorState(
                        it.error
                    )
                    is CategoryScreenState.Error -> showErrorState(it.error)
                }
            })

        binding.categoryLayoutId.categoriesSwipeLayout.setOnRefreshListener {
            binding.categoryLayoutId.categoriesSwipeLayout.isRefreshing = false
            categoryViewModel.fetchData()
            binding.categoryLayoutId.rvCategoryFragment.scheduleLayoutAnimation()
        }
        return binding.root
    }

    private fun showLoadingState() {
        binding.categoryLayoutId.rvCategoryFragment isNeedToShow false
        binding.categoryLayoutId.ivCategoryError isNeedToShow false
        binding.categoryLayoutId.tvCategoriesError isNeedToShow false
        binding.categoryLayoutId.pbCategoryFragment isNeedToShow true
        stopAnimation()
    }

    private fun showSuccessStateFromApi(
        adapter: CategoryRVAdapter,
        categories: List<String>
    ) {
        binding.categoryLayoutId.pbCategoryFragment isNeedToShow false
        binding.categoryLayoutId.ivCategoryError isNeedToShow false
        binding.categoryLayoutId.tvCategoriesError isNeedToShow false
        binding.categoryLayoutId.rvCategoryFragment isNeedToShow true
        stopAnimation()
        adapter.updateCategoryList(categories)
        categoryViewModel.currentCategories = categories
    }

    private fun showSuccessStateFromCache(
        adapter: CategoryRVAdapter,
        categories: List<String>
    ) {
        binding.categoryLayoutId.pbCategoryFragment isNeedToShow false
        binding.categoryLayoutId.ivCategoryError isNeedToShow false
        binding.categoryLayoutId.tvCategoriesError isNeedToShow false
        binding.categoryLayoutId.rvCategoryFragment isNeedToShow true
        stopAnimation()
        adapter.updateCategoryList(categories)
        categoryViewModel.currentCategories = categories
        getSnackBarCategoriesFromCache(
            requireView(),
            requireContext()
        ).show()
    }

    private fun showErrorState(error: String) {
        binding.categoryLayoutId.pbCategoryFragment isNeedToShow false
        binding.categoryLayoutId.rvCategoryFragment isNeedToShow false
        binding.categoryLayoutId.ivCategoryError isNeedToShow true
        binding.categoryLayoutId.tvCategoriesError isNeedToShow true
        startAnimation()
        binding.categoryLayoutId.tvCategoriesError.text = error
    }

    private fun startAnimation() {
        val animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.chuck_exception_icon_rotate)
        binding.categoryLayoutId.ivCategoryError.startAnimation(animation)
    }

    private fun stopAnimation() {
        binding.categoryLayoutId.ivCategoryError.animation = null
    }

    override fun onFactClick(category: String) {
        findNavController().navigate(R.id.fact_fragment_navigation, Bundle().apply {
            putString(
                CATEGORY, category
            )
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}