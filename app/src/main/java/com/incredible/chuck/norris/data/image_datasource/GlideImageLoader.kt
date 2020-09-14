package com.incredible.chuck.norris.data.image_datasource

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader(private val context: Context) : ImageLoader {

    override fun loadImageFromUrl(url: String, view: ImageView) {
        Glide.with(context).load(url).into(view)
    }

    override fun loadImageFromResources(drawable: Drawable, view: ImageView) {
        Glide.with(context).load(drawable).into(view)
    }
}