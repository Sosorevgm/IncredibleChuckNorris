package com.incredible.chuck.norris.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {

    @Query("SELECT * FROM categories_table")
    suspend fun getCategories(): List<CategoryEntity>

    @Query("SELECT * FROM facts_table WHERE category =:category")
    suspend fun getAllFactsByCategory(category: String): List<FactEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putCategories(categories: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putFact(fact: FactEntity)

}