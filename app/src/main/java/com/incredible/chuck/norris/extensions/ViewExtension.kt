package com.incredible.chuck.norris.extensions

import android.view.View

infix fun View.isNeedToShow(flag: Boolean) {
    visibility = if (flag) {
        View.VISIBLE
    } else {
        View.GONE
    }
}