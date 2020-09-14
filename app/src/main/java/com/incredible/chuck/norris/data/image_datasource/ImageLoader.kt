package com.incredible.chuck.norris.data.image_datasource

import android.graphics.drawable.Drawable
import android.widget.ImageView

interface ImageLoader {
    fun loadImageFromUrl(url: String, view: ImageView)
    fun loadImageFromResources(drawable: Drawable, view: ImageView)
}