package com.zoom_machine.feature_detailsscreen.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zoom_machine.feature_detailsscreen.data.ProductSpecification
import com.zoom_machine.feature_detailsscreen.databinding.ItemViewPagerBinding


class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    private var items: List<ProductSpecification> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemViewPagerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(newList: List<ProductSpecification>) {
        items = newList
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemViewPagerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductSpecification) {
            binding.run {
                textCPU.text = item.CPU
                textCamera.text = item.camera
                textMemory.text = item.memory
                textFlash.text = item.flash
            }
        }
    }
}

