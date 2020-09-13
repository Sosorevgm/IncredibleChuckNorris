package com.incredible.chuck.norris.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun getSnackBarConnectionProblems(view: View): Snackbar {
    return Snackbar.make(view, "Oops, connection problems", Snackbar.LENGTH_LONG)
}