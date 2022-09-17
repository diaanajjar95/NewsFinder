package com.diaa.newsfinder.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diaa.newsfinder.databinding.ViewItemNewsVerticalBinding
import com.diaa.newsfinder.ui.home.adapters.viewholders.VerticalNewsVH
import com.diaa.newsfinder.ui.home.models.VerticalNewsItem

class VerticalNewsAdapter : RecyclerView.Adapter<VerticalNewsVH>() {

    private var verticalItems = mutableListOf<VerticalNewsItem>()
    private lateinit var binding: ViewItemNewsVerticalBinding
    private var onItemClickListener: OnItemClickListener? = null

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

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClicked(it, verticalItems[position], position)
        }

    }

    override fun getItemCount(): Int = verticalItems.size

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClicked(view: View, item: VerticalNewsItem, position: Int)
    }

}