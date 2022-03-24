package com.zoom_machine.feature_mainscreen.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zoom_machine.api.services.data.HotSales
import com.zoom_machine.feature_mainscreen.R
import com.zoom_machine.feature_mainscreen.databinding.ItemHotSaleBinding


class HotSaleAdapter(
    private val onItemClick: (position: Int) -> Unit,
) : RecyclerView.Adapter<HotSaleAdapter.ViewHolder>() {
    private var items: List<HotSales> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemHotSaleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(newList: List<HotSales>) {
        items = newList
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemHotSaleBinding,
        onItemClick: (position: Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick(absoluteAdapterPosition)
            }
        }

        fun bind(item: HotSales) {
            binding.run {
                if (item.is_new == null || item.is_new == false) {
                    imageNew.visibility = View.GONE
                }
                textBrand.text = item.title
                textBrandDescription.text = item.subtitle
                Glide.with(itemView)
                    .load(item.picture)
                    .error(R.drawable.connect_error)
                    .into(topSaleBanner)
            }
        }
    }
}

