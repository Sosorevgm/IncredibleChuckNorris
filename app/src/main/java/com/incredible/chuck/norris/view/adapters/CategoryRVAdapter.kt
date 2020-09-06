package com.incredible.chuck.norris.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.incredible.chuck.norris.R
import kotlinx.android.synthetic.main.category_card_view.view.*

class CategoryRVAdapter(
    var categoryList: List<String>,
    private val listener: CategoryClickListener
) : RecyclerView.Adapter<CategoryRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.category_card_view, parent, false),
            listener
        )

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    inner class ViewHolder(
        val view: View,
        private val listener: CategoryClickListener
    ) : RecyclerView.ViewHolder(view) {

        fun bind(category: String) {
            view.tv_category_text.text = category
            view.cv_category_root.setOnClickListener {
                listener.onFactClick(category)
            }
        }
    }
}