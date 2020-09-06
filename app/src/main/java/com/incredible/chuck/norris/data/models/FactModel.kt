package com.incredible.chuck.norris.data.models

import com.google.gson.annotations.SerializedName

data class FactModel(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("icon_url") val icon_url: String,
    @field:SerializedName("value") val fact: String,
    @field:SerializedName("created_at") val date: String
)