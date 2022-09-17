package com.diaa.newsfinder.ui.base.bindingadapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

interface BindingAdapters {

    @BindingAdapter(
        "appImageUrl",
        "appCorners",
        requireAll = false
    )
    fun AppCompatImageView.setImage(
        imageUrl: String?,
        corners: Float = 0f
    )

}