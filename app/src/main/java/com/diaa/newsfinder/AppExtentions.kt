package com.diaa.newsfinder

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun Fragment.openWebBrowser(url: String) {
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    activity!!.startActivity(i)
}

@SuppressLint("SimpleDateFormat")
fun String.changeDateFormatFrom(fromFormat: String): String {
    var newDate = ""
    try {
        val sdfSource = SimpleDateFormat(fromFormat)
        val date: Date = sdfSource.parse(this) as Date
        val sdfDestination = SimpleDateFormat("dd/MM/yyyy")
        newDate = sdfDestination.format(date)
    } catch (e: Exception) {
        println("Parse Exception : $e")
    }
    return newDate
}