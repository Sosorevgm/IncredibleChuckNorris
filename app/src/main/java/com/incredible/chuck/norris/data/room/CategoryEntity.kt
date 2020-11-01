package com.incredible.chuck.norris.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories_table")
class CategoryEntity(
    @field:PrimaryKey
    val category: String
)