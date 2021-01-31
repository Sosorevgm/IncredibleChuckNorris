package com.incredible.chuck.norris.common

import android.view.View

data class ErrorModel(
    var title: String? = null,
    var titleResId: Int? = null,
    var message: String? = null,
    var messageResId: Int? = null,
    var buttonText: String? = null,
    var buttonTextResId: Int? = null,
    var buttonBackground: Int? = null,
    var buttonListener: View.OnClickListener? = null,
    var image: Int? = null,
    var imageAnimation: Int? = null
)