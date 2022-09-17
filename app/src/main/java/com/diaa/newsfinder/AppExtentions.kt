package com.diaa.newsfinder

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

fun Fragment.openWebBrowser(url: String) {
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    activity!!.startActivity(i)
}