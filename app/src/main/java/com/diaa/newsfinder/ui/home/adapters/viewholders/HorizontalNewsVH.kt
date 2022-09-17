package com.diaa.newsfinder.ui.home.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.diaa.newsfinder.databinding.ViewItemNewsHorizontalBinding
import com.diaa.newsfinder.ui.home.models.HorizontalNewsItem

class HorizontalNewsVH(
    private val binding: ViewItemNewsHorizontalBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(horizontalNews: HorizontalNewsItem) {
        binding.item = horizontalNews
    }

}