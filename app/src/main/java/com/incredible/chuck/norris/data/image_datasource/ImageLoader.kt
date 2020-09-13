package com.incredible.chuck.norris.data.image_datasource

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(url: String, view: ImageView)
}