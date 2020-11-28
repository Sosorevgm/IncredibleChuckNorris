package com.incredible.chuck.norris.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.data.screen_state.CategoryScreenState
import com.incredible.chuck.norris.databinding.FragmentCategoryBinding
import com.incredible.chuck.norris.extensions.isNeedToShow
import com.incredible.chuck.norris.navigation.Screens
import com.incredible.chuck.norris.utils.getSnackBarCategoriesFromCache
import com.incredible.chuck.norris.view.adapters.CategoryClickListener
import com.incredible.chuck.norris.view.adapters.CategoryRVAdapter
import com.incredible.chuck.norris.view_model.CategoryViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.terrakok.cicerone.Router

class CategoryFragment : Fragment(), CategoryClickListener {

    companion object {
        fun getInstance() = CategoryFragment()
    }

    private val viewModel: CategoryViewModel by viewModel()
    private val router: Router by inject()

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CategoryRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        adapter = CategoryRVAdapter(listOf(), this)
        binding.categoryLayoutId.rvCategoryFragment.adapter = adapter

        if (viewModel.currentCategories != null) {
            showSuccessState(viewModel.currentCategories!!)
        } else {
            viewModel.fetchData()
        }

        viewModel.screenState.observe(viewLifecycleOwner,
            {
                when (it) {
                    is CategoryScreenState.Loading -> showLoadingState()
                    is CategoryScreenState.SuccessFromApi -> showSuccessState(it.categories)
                    is CategoryScreenState.SuccessFromCache -> {
                        showSuccessState(it.categories)
                        getSnackBarCategoriesFromCache(requireView(), requireContext()).show()
                    }
                    is CategoryScreenState.ErrorCacheIsEmpty -> showErrorState(it.error)
                    is CategoryScreenState.Error -> showErrorState(it.error)
                }
            })

        binding.categoryLayoutId.categoriesSwipeLayout.setOnRefreshListener {
            binding.categoryLayoutId.categoriesSwipeLayout.isRefreshing = false
            viewModel.fetchData()
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

    private fun showSuccessState(
        categories: List<String>
    ) {
        binding.categoryLayoutId.pbCategoryFragment isNeedToShow false
        binding.categoryLayoutId.ivCategoryError isNeedToShow false
        binding.categoryLayoutId.tvCategoriesError isNeedToShow false
        binding.categoryLayoutId.rvCategoryFragment isNeedToShow true
        stopAnimation()
        adapter.updateCategoryList(categories)
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
        router.navigateTo(Screens.FactScreen(category))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}