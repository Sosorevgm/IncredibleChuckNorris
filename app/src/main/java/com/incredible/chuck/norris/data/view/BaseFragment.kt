package com.incredible.chuck.norris.data.view

import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.incredible.chuck.norris.extensions.isNeedToShow

abstract class BaseFragment : Fragment() {

    private lateinit var successLayout: View
    private lateinit var loadingProgressBar: View
    private lateinit var errorLayout: ErrorViewWidget

    fun initFragmentLayout(
        successLayout: View,
        loadingProgressBar: View,
        errorLayout: ErrorViewWidget
    ) {
        this.successLayout = successLayout
        this.loadingProgressBar = loadingProgressBar
        this.errorLayout = errorLayout
    }

    open fun showSuccess(data: Any) {
        errorLayout isNeedToShow false
        loadingProgressBar isNeedToShow false
        successLayout isNeedToShow true
    }

    open fun showLoading() {
        successLayout isNeedToShow false
        errorLayout isNeedToShow false
        loadingProgressBar isNeedToShow true
    }

    open fun showError(errorModel: ErrorModel, errorListener: View.OnClickListener? = null) {
        successLayout isNeedToShow false
        loadingProgressBar isNeedToShow false
        errorLayout isNeedToShow true
        errorLayout.setModel(errorModel, errorListener)
    }
}