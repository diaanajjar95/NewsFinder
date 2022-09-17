package com.diaa.newsfinder.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.diaa.newsfinder.AppListener

open class BaseFragment : Fragment() {

    private lateinit var listener: AppListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AppListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement AppListener")
        }
    }

    protected open fun showLoader(show: Boolean) {
        if (show) {
            listener.showLoader()
        } else {
            listener.hideLoader()
        }
    }

    protected fun showMessage(message: String) {
        listener.showMessage(message)
    }

}