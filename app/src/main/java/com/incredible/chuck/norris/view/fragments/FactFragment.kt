package com.incredible.chuck.norris.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.data.screen_state.FactScreenState
import com.incredible.chuck.norris.extensions.hide
import com.incredible.chuck.norris.extensions.show
import com.incredible.chuck.norris.utils.getDateString
import com.incredible.chuck.norris.view_model.FactViewModel
import kotlinx.android.synthetic.main.fact_layout.view.*
import kotlinx.android.synthetic.main.fragment_fact.view.*
import org.koin.android.ext.android.inject

class FactFragment : Fragment() {

    companion object {
        const val CATEGORY = "category"
    }

    private val viewModel: FactViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_fact, container, false)
        val category = arguments?.getString(CATEGORY)

        category?.let {
            root.tv_fragment_category_name.text = category
            viewModel.fetchData(it)
        }
        viewModel.screenState.observe(viewLifecycleOwner, Observer<FactScreenState> {
            when (it) {
                is FactScreenState.Loading -> {
                    root.pb_fact_fragment.show()
                }
                is FactScreenState.Success -> {
                    root.pb_fact_fragment.hide()
                    Glide.with(this).load(it.fact.icon_url).into(root.iv_fact_icon)
                    root.tv_fact_text.text = it.fact.fact
                    root.tv_fact_date.text = getDateString(it.fact.date)
                }
                is FactScreenState.Error -> {
                    root.pb_fact_fragment.hide()
                    root.tv_fact_error.text = it.error
                }
            }
        })

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
}