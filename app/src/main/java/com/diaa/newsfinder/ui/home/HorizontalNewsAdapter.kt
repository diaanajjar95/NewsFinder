package com.diaa.newsfinder.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diaa.newsfinder.databinding.ViewItemNewsHorizontalBinding
import com.diaa.newsfinder.ui.home.models.HorizontalNewsItem

class HorizontalNewsAdapter : RecyclerView.Adapter<HorizontalNewsVH>() {

    private var horizontalItems = mutableListOf<HorizontalNewsItem>()
    private lateinit var binding: ViewItemNewsHorizontalBinding

    fun setItems(items: List<HorizontalNewsItem>) {
        horizontalItems.addAll(items)
        notifyItemRangeInserted(horizontalItems.size, items.size)
    }

    fun clear() {
        horizontalItems.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalNewsVH {
        binding = ViewItemNewsHorizontalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HorizontalNewsVH(binding)
    }

    override fun onBindViewHolder(holder: HorizontalNewsVH, position: Int) {
        holder.bind(horizontalItems[position])
    }

    override fun getItemCount(): Int = horizontalItems.size
}