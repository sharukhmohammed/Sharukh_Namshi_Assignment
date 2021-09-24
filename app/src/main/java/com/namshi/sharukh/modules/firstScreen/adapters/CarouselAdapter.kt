package com.namshi.sharukh.modules.firstScreen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.namshi.sharukh.R
import com.namshi.sharukh.base.BaseAdapter
import com.namshi.sharukh.base.BaseViewHolder
import com.namshi.sharukh.databinding.ItemCarouselSubBinding
import com.namshi.sharukh.models.Image
import com.namshi.sharukh.modules.common.ActionListener
import com.namshi.sharukh.utils.clearAndAddAll
import com.namshi.sharukh.utils.onClick
import com.namshi.sharukh.utils.load

class CarouselAdapter(private val listener: ActionListener) : BaseAdapter<CarouselAdapter.Holder>() {
    private val items: MutableList<Image> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_carousel_sub, parent, false)).apply {
            itemView.onClick {
                val image = items[adapterPosition]
                listener.onItemClick(image)
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val widget = items[holder.adapterPosition]
        holder.bind(widget)
    }

    override fun getItemCount() = items.size

    fun setData(data: List<Image>) {
        items.clearAndAddAll(data)
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : BaseViewHolder<Image>(itemView) {
        override fun bind(data: Image) {
            val binding = ItemCarouselSubBinding.bind(itemView)
            binding.carouselImage load data
        }
    }
}