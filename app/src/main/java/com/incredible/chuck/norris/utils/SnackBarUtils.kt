package com.incredible.chuck.norris.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.incredible.chuck.norris.R

fun getSnackBarCategoriesFromCache(view: View, context: Context): Snackbar {
    val snackBar = Snackbar.make(view, context.getString(R.string.categories_from_cache), Snackbar.LENGTH_LONG)
    snackBar.view.background = ContextCompat.getDrawable(context, R.drawable.snack_bar_background)
    snackBar.setActionTextColor(ContextCompat.getColor(context, R.color.snack_bar_action_text_color))
    return snackBar
}

fun getSnackBarFactsFromCache(view: View, context: Context): Snackbar {
    val snackBar = Snackbar.make(view, context.getString(R.string.facts_from_cache), Snackbar.LENGTH_LONG)
    snackBar.view.background = ContextCompat.getDrawable(context, R.drawable.snack_bar_background)
    snackBar.setActionTextColor(ContextCompat.getColor(context, R.color.snack_bar_action_text_color))
    return snackBar
}