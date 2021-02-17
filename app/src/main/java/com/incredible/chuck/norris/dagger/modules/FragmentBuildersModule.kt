package com.incredible.chuck.norris.dagger.modules

import com.incredible.chuck.norris.features.categories_feature.CategoryFragment
import com.incredible.chuck.norris.features.fact_feature.FactFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeCategoryFragment(): CategoryFragment

    @ContributesAndroidInjector
    abstract fun contributeFactFragment(): FactFragment
}