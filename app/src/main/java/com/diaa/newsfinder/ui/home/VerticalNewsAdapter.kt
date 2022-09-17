package com.diaa.newsfinder.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diaa.newsfinder.databinding.ViewItemNewsVerticalBinding
import com.diaa.newsfinder.ui.home.models.VerticalNewsItem

class VerticalNewsAdapter : RecyclerView.Adapter<VerticalNewsVH>() {

    private var verticalItems = mutableListOf<VerticalNewsItem>()
    private lateinit var binding: ViewItemNewsVerticalBinding

    fun setItems(items: List<VerticalNewsItem>) {
        verticalItems.addAll(items)
        notifyItemRangeInserted(verticalItems.size, items.size)
    }

    fun clear() {
        verticalItems.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalNewsVH {
        binding = ViewItemNewsVerticalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VerticalNewsVH(binding)
    }

    override fun onBindViewHolder(holder: VerticalNewsVH, position: Int) {
        holder.bind(verticalItems[position])
    }

    override fun getItemCount(): Int = verticalItems.size
}