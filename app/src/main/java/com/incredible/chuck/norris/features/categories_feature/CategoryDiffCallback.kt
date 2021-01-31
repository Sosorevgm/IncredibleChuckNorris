package com.incredible.chuck.norris.features.categories_feature

import androidx.recyclerview.widget.DiffUtil

class CategoryDiffCallback(
    private val oldCategories: List<String>,
    private val newCategories: List<String>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldCategories.size

    override fun getNewListSize() = newCategories.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldCategories[oldItemPosition] == newCategories[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldCategories[oldItemPosition] == newCategories[newItemPosition]
}