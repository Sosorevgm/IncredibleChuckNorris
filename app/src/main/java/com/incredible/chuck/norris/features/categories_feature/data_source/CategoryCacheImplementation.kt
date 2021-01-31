package com.incredible.chuck.norris.features.categories_feature.data_source

import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.data.room.AppDatabase
import com.incredible.chuck.norris.data.room.CategoryEntity
import com.incredible.chuck.norris.features.categories_feature.CategoryScreenState
import com.incredible.chuck.norris.common.ErrorModel

class CategoryCacheImplementation(
    private val room: AppDatabase
) : CategoryDataSource<CategoryScreenState> {

    override suspend fun getCategories(): CategoryScreenState {
        val categories = room.appDao.getCategories().map { it.category }
        return if (categories.isNotEmpty()) {
            CategoryScreenState.SuccessFromCache(categories)
        } else {
            CategoryScreenState.Error(
                ErrorModel(
                    titleResId = R.string.categories_from_cache_is_empty,
                    buttonTextResId = R.string.retry,
                    image = R.drawable.chuck_main_icon,
                    imageAnimation = R.anim.chuck_exception_icon_rotate
                )
            )
        }
    }

    suspend fun putCategories(categories: List<String>) {
        room.appDao.putCategories(categories.map { CategoryEntity(it) })
    }
}