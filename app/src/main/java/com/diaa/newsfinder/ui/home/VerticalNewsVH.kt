package com.diaa.newsfinder.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.diaa.newsfinder.databinding.ViewItemNewsHorizontalBinding
import com.diaa.newsfinder.databinding.ViewItemNewsVerticalBinding
import com.diaa.newsfinder.ui.home.models.HorizontalNewsItem
import com.diaa.newsfinder.ui.home.models.VerticalNewsItem

class VerticalNewsVH(
    private val binding: ViewItemNewsVerticalBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(verticalNews: VerticalNewsItem) {
        binding.item = verticalNews
    }

}