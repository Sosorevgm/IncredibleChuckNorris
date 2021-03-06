package com.incredible.chuck.norris.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FactModel(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("icon_url") val iconUrl: String,
    @field:SerializedName("value") var fact: String,
    @field:SerializedName("created_at") val date: String
) : Parcelable