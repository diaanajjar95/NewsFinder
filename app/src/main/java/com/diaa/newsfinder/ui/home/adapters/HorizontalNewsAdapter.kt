package com.diaa.newsfinder.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diaa.newsfinder.databinding.ViewItemNewsHorizontalBinding
import com.diaa.newsfinder.ui.home.adapters.viewholders.HorizontalNewsVH
import com.diaa.newsfinder.ui.home.models.HorizontalNewsItem

class HorizontalNewsAdapter : RecyclerView.Adapter<HorizontalNewsVH>() {

    private var horizontalItems = mutableListOf<HorizontalNewsItem>()
    private lateinit var binding: ViewItemNewsHorizontalBinding
    private var onItemClickListener: OnItemClickListener? = null

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

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClicked(it, horizontalItems[position], position)
        }

    }

    override fun getItemCount(): Int = horizontalItems.size

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClicked(view: View, item: HorizontalNewsItem, position: Int)
    }
}