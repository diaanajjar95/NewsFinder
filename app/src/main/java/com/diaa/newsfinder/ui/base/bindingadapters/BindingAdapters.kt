package com.diaa.newsfinder.ui.base.bindingadapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

interface BindingAdapters {

    @BindingAdapter(
        "appImageUrl",
        requireAll = false
    )
    fun AppCompatImageView.setImage(
        imageUrl: String?
    )

}