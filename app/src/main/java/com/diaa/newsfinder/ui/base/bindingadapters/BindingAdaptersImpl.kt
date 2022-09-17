package com.diaa.newsfinder.ui.base.bindingadapters

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.diaa.newsfinder.R

class BindingAdaptersImpl : BindingAdapters {
    override fun AppCompatImageView.setImage(imageUrl: String?, corners: Float) {
        Glide.with(this).load(imageUrl).error(R.drawable.ic_news_placeholder)
            .placeholder(R.drawable.ic_news_placeholder).into(this)
    }
}