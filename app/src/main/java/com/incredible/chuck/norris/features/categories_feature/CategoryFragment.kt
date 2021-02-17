package com.incredible.chuck.norris.features.categories_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.incredible.chuck.norris.common.BaseFragment
import com.incredible.chuck.norris.databinding.FragmentCategoryBinding
import com.incredible.chuck.norris.utils.getSnackBarCategoriesFromCache
import javax.inject.Inject

class CategoryFragment : BaseFragment(), CategoryRVAdapter.IListener, View.OnClickListener {

    companion object {
        fun getInstance() = CategoryFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(CategoryViewModel::class.java)
    }

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CategoryRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        adapter = CategoryRVAdapter(listOf(), this)
        binding.rvCategoryFragment.adapter = adapter

        initFragmentLayout(
            binding.categorySuccessLayout,
            binding.progressBarMain,
            binding.categoryErrorLayout.errorWidgetLayout
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

        binding.categoriesSwipeLayout.setOnRefreshListener {
            binding.categoriesSwipeLayout.isRefreshing = false
            viewModel.fetchData()
        }
        return binding.root
    }

    override fun showSuccess(data: Any) {
        super.showSuccess(data)
        val categories = data as List<String>
        adapter.updateCategoryList(categories)
    }

    override fun onCategoryClick(category: String) {
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