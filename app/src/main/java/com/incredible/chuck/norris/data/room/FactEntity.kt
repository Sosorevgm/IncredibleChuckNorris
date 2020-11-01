package com.incredible.chuck.norris.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facts_table")
class FactEntity(
    @field:PrimaryKey
    @field:ColumnInfo(name = "id")
    val id: String,
    @field:ColumnInfo(name = "category")
    val category: String,
    @field:ColumnInfo(name = "url")
    val url: String,
    @field:ColumnInfo(name = "icon_url")
    val iconUrl: String,
    @field:ColumnInfo(name = "value")
    val value: String,
    @field:ColumnInfo(name = "created_at")
    val date: String
)