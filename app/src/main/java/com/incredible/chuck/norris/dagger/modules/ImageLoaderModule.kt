package com.incredible.chuck.norris.dagger.modules

import com.incredible.chuck.norris.data.image_datasource.GlideImageLoader
import com.incredible.chuck.norris.data.image_datasource.ImageLoader
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ImageLoaderModule {

    @Singleton
    @Binds
    abstract fun bindsGlideImageLoader(glide: GlideImageLoader): ImageLoader
}