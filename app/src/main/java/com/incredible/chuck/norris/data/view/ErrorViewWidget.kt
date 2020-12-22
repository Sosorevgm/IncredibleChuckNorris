package com.incredible.chuck.norris.data.view

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.extensions.isNeedToShow

class ErrorViewWidget(
    context: Context,
    attrs: AttributeSet
) : ConstraintLayout(context, attrs) {

    private lateinit var model: ErrorModel

    fun setModel(errorModel: ErrorModel, errorListener: OnClickListener? = null) {
        model = errorModel
        model.buttonListener = errorListener
        initModel()
    }

    private val title: TextView? by lazy {
        findViewById(R.id.error_widget_title)
    }
    private val message: TextView? by lazy {
        findViewById(R.id.error_widget_message)
    }
    private val image: ImageView? by lazy {
        findViewById(R.id.error_widget_image)
    }
    private val button: ConstraintLayout? by lazy {
        findViewById(R.id.error_widget_button)
    }
    private val buttonText: TextView? by lazy {
        findViewById(R.id.error_widget_button_text)
    }

    private fun initModel() {
        if (model.titleResId != null) {
            title?.text = context.getString(model.titleResId!!)
        } else if (!model.title.isNullOrEmpty()) {
            title?.text = model.title
        }

        if (model.messageResId != null) {
            message?.text = context.getString(model.messageResId!!)
        } else if (!model.message.isNullOrEmpty()) {
            message?.text = model.message
        }

        if (model.buttonTextResId != null) {
            buttonText?.let {
                it.text = context.getString(model.buttonTextResId!!)
            }
        } else if (!model.buttonText.isNullOrEmpty()) {
            buttonText?.let {
                it.text = model.buttonText
            }
        } else {
            button?.let {
                it isNeedToShow false
            }
        }

        model.buttonBackground?.let {
            button?.background = context.getDrawable(it)
        }

        model.image?.let {
            image?.setImageResource(it)
        }

        model.imageAnimation?.let {
            image?.startAnimation(AnimationUtils.loadAnimation(context, it))
        }

        model.buttonListener?.let {
            button?.setOnClickListener(it)
        }
    }
}