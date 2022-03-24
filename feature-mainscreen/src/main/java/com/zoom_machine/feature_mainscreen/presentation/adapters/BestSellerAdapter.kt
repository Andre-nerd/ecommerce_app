package com.zoom_machine.feature_mainscreen.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zoom_machine.api.services.data.BestSeller
import com.zoom_machine.feature_mainscreen.R
import com.zoom_machine.feature_mainscreen.databinding.ItemBestSellerBinding


class BestSellerAdapter(
    private val labelFavorite:Drawable?,
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<BestSellerAdapter.ViewHolder>() {
    private var items: List<BestSeller> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemBestSellerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(labelFavorite, itemBinding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(newList: List<BestSeller>) {
        items = newList
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val labelFavorite:Drawable?,
        private val binding: ItemBestSellerBinding,
        onItemClick: (position: Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick(absoluteAdapterPosition)
            }
        }

        fun bind(item: BestSeller) {
            binding.run {
                if (item.is_favorites != null && item.is_favorites == true) {
                    imageFavorite.setImageDrawable(labelFavorite)
                }
                textDiscountPrice.text = item.discount_price.toString() + "$"
                textNormalPrice.text = item.price_without_discount.toString() + "$"
                textDescription.text = item.title
                Glide.with(itemView)
                    .load(item.picture)
                    .error(R.drawable.no_image)
                    .into(bestSellerBanner)
            }
        }
    }
}
