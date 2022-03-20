package com.zoom_machine.ecommerce_app.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zoom_machine.ecommerce_app.R
import com.zoom_machine.ecommerce_app.databinding.ItemTopMenuBinding
import com.zoom_machine.ecommerce_app.presentation.ui.ui_components.TopMenuItem

class TopMenuAdapter(
    private val context: Context,
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<TopMenuAdapter.ViewHolder>() {
    private var items: List<TopMenuItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemTopMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(context, itemBinding, onItemClick)
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
        private val context: Context,
        private val binding: ItemTopMenuBinding,
        onItemClick: (position: Int) -> Unit,
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
                    topMenuCircle.setColorFilter(ContextCompat.getColor(context, R.color.ocher))
                    topMenuIcon.setColorFilter(ContextCompat.getColor(context, R.color.white))
                } else {
                    topMenuCircle.setColorFilter(ContextCompat.getColor(context, R.color.white))
                    topMenuIcon.setColorFilter(ContextCompat.getColor(context, R.color.light_grey))
                }
                topMenuIcon.setImageDrawable(context.getDrawable(item.icon))
                topMenuTitle.text = context.resources.getString(item.title)
            }
        }
    }
}

