package com.incredible.chuck.norris.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.extensions.hide
import com.incredible.chuck.norris.extensions.show
import com.incredible.chuck.norris.model.CategoryScreenState
import com.incredible.chuck.norris.view.adapters.CategoryClickListener
import com.incredible.chuck.norris.view.adapters.CategoryRVAdapter
import com.incredible.chuck.norris.view_model.CategoryViewModel
import kotlinx.android.synthetic.main.category_layout.view.*

class CategoryFragment : Fragment(), CategoryClickListener {

    private val viewModel = CategoryViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_category, container, false)
        viewModel.updateData()
        viewModel.screenState.observe(viewLifecycleOwner,
            Observer<CategoryScreenState> {
                when (it) {
                    is CategoryScreenState.Loading -> {
                        root.pb_category_fragment.show()
                        root.tv_error_category_fragment.hide()
                        root.rv_category_fragment.hide()
                    }
                    is CategoryScreenState.Success -> {
                        root.pb_category_fragment.hide()
                        root.tv_error_category_fragment.hide()
                        root.rv_category_fragment.show()
                        val adapter = CategoryRVAdapter(it.categories, this)
                        root.rv_category_fragment.adapter = adapter
                    }
                    is CategoryScreenState.Error -> {
                        root.pb_category_fragment.hide()
                        root.tv_error_category_fragment.show()
                        root.rv_category_fragment.hide()
                    }
                }
            })
        return root
    }

    override fun onFactClick(category: String) {

    }
}