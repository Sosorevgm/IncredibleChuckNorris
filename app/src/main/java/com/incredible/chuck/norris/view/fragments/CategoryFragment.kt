package com.incredible.chuck.norris.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.incredible.chuck.norris.data.screen_state.CategoryScreenState
import com.incredible.chuck.norris.data.view.BaseFragment
import com.incredible.chuck.norris.databinding.FragmentCategoryBinding
import com.incredible.chuck.norris.utils.getSnackBarCategoriesFromCache
import com.incredible.chuck.norris.view.adapters.CategoryClickListener
import com.incredible.chuck.norris.view.adapters.CategoryRVAdapter
import com.incredible.chuck.norris.view_model.CategoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : BaseFragment(), CategoryClickListener, View.OnClickListener {

    companion object {
        fun getInstance() = CategoryFragment()
    }

    private val viewModel: CategoryViewModel by viewModel()

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

        initFragmentLayout(
            binding.categoryLayoutId.categorySuccessLayout,
            binding.categoryLayoutId.progressBarMain,
            binding.categoryLayoutId.categoryErrorLayout.errorWidgetLayout
        )

        if (viewModel.currentCategories != null) {
            showSuccess(viewModel.currentCategories!!)
        } else {
            viewModel.fetchData()
        }

        viewModel.screenState.observe(viewLifecycleOwner,
            {
                when (it) {
                    is CategoryScreenState.Loading -> showLoading()
                    is CategoryScreenState.SuccessFromApi -> showSuccess(it.categories)
                    is CategoryScreenState.SuccessFromCache -> {
                        showSuccess(it.categories)
                        getSnackBarCategoriesFromCache(requireView(), requireContext()).show()
                    }
                    is CategoryScreenState.Error -> showError(it.errorModel, this)
                }
            })

        binding.categoryLayoutId.categoriesSwipeLayout.setOnRefreshListener {
            binding.categoryLayoutId.categoriesSwipeLayout.isRefreshing = false
            viewModel.fetchData()
        }
        return binding.root
    }

    override fun showSuccess(data: Any) {
        super.showSuccess(data)
        val categories = data as List<String>
        adapter.updateCategoryList(categories)
    }

    override fun onFactClick(category: String) {
        viewModel.factClicked(category)
    }

    override fun onClick(v: View?) {
        viewModel.fetchData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}