package com.zoom_machine.feature_mainscreen.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zoom_machine.feature_mainscreen.databinding.ItemTopMenuBinding
import com.zoom_machine.feature_mainscreen.data.ColorSettingTopMenu
import com.zoom_machine.feature_mainscreen.data.TopMenuItem


class TopMenuAdapter(
    private val colors: ColorSettingTopMenu,
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<TopMenuAdapter.ViewHolder>() {
    private var items: List<TopMenuItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemTopMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding, onItemClick, colors)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(newList: List<TopMenuItem>) {
        items = newList
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemTopMenuBinding,
        onItemClick: (position: Int) -> Unit,
        private val colors: ColorSettingTopMenu
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick(absoluteAdapterPosition)
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(item: TopMenuItem) {
            binding.run {
                if (item.isSelected) {
                    topMenuCircle.setColorFilter(colors.backgroundSelected)
                    topMenuIcon.setColorFilter(colors.icoSelected)
                } else {
                    topMenuCircle.setColorFilter(colors.backgroundUnSelected)
                    topMenuIcon.setColorFilter(colors.icoUnSelected)
                }
                topMenuIcon.setImageDrawable(item.icon)
                topMenuTitle.text = item.title
            }
        }
    }
}

