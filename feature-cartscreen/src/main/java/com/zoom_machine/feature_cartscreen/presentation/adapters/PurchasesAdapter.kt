package com.zoom_machine.feature_cartscreen.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zoom_machine.feature_cartscreen.databinding.ItemPurchasesBinding

class PurchasesAdapter(
    private val onItemClick: (position: Int, countPurchase: Int) -> Unit
) : RecyclerView.Adapter<PurchasesAdapter.ViewHolder>() {
    private var items: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemPurchasesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding, onItemClick)
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
        private val binding: ItemPurchasesBinding,
        private val onItemClick: (position: Int, countPurchase: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.plusMinusContainer.plus.setOnClickListener {
                val count = plusCount(binding.plusMinusContainer.count.value ?: 0)
                binding.plusMinusContainer.setDigit(count)
                onItemClick(adapterPosition, count)
            }
            binding.plusMinusContainer.minus.setOnClickListener {
                val count = minusCount(binding.plusMinusContainer.count.value ?: 0)
                binding.plusMinusContainer.setDigit(count)
                onItemClick(adapterPosition, count)
            }
        }

        fun bind(item: String) {
//                Glide.with(itemView)
//                    .load(item)
//                    .error(R.drawable.no_image)
//                    .into(binding.imageTopGallery)
        }

        private fun plusCount(value: Int): Int {
            return if (value < 10) {
                value + 1
            } else {
                value
            }
        }

        private fun minusCount(value: Int): Int {
            return if (value > 0) {
                value - 1
            } else {
                value
            }
        }
    }
}
