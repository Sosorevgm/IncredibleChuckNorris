package com.incredible.chuck.norris.data.image_datasource

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader(private val context: Context) : ImageLoader {
    override fun loadImage(url: String, view: ImageView) {
        Glide.with(context).load(url).into(view)
    }
}