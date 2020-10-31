package com.incredible.chuck.norris.data.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AppDao {

    @Query("SELECT * FROM categories_table")
    suspend fun getCategories(): List<CategoryEntity>

    @Query("SELECT * FROM facts_table WHERE category =:category")
    suspend fun getFactByCategory(category: String): FactEntity

}