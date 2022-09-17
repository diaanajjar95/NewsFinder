package com.diaa.newsfinder

interface AppListener {

    fun showLoader()
    fun hideLoader()
    fun showMessage(message: String?)

}