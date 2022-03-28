package com.zoom_machine.feature_detailsscreen.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zoom_machine.feature_detailsscreen.R
import com.zoom_machine.feature_detailsscreen.databinding.ItemTopBannerBinding

class TopGalleryAdapter  : RecyclerView.Adapter<TopGalleryAdapter.ViewHolder>() {
    private var items: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemTopBannerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(newList: List<String>) {
        items = newList
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemTopBannerBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
                Glide.with(itemView)
                    .load(item)
                    .error(R.drawable.no_image)
                    .into(binding.imageTopGallery)
        }
    }
}

