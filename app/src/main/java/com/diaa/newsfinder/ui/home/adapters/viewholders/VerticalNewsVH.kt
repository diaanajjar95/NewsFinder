package com.diaa.newsfinder.ui.home.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.diaa.newsfinder.databinding.ViewItemNewsVerticalBinding
import com.diaa.newsfinder.ui.home.models.VerticalNewsItem

class VerticalNewsVH(
    private val binding: ViewItemNewsVerticalBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(verticalNews: VerticalNewsItem) {
        binding.item = verticalNews
    }

}