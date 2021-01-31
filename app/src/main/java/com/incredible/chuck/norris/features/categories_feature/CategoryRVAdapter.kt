package com.incredible.chuck.norris.features.categories_feature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.databinding.CategoryCardViewBinding

class CategoryRVAdapter(
    private var categoryList: List<String>,
    private val listener: IListener
) : RecyclerView.Adapter<CategoryRVAdapter.ViewHolder>() {

    interface IListener {
        fun onCategoryClick(category: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.category_card_view, parent, false),
            listener
        )

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    fun updateCategoryList(newCategoryList: List<String>) {
        val difResult = DiffUtil.calculateDiff(CategoryDiffCallback(categoryList, newCategoryList))
        categoryList = newCategoryList
        difResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(
        val view: View,
        private val listener: IListener
    ) : RecyclerView.ViewHolder(view) {

        private val binding = CategoryCardViewBinding.bind(view)

        fun bind(category: String) {
            binding.tvCategoryText.text = category.capitalize()
            binding.cvCategoryRoot.setOnClickListener {
                listener.onCategoryClick(category)
            }
        }
    }
}