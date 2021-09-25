package com.namshi.sharukh.modules.homeScreen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.namshi.sharukh.R
import com.namshi.sharukh.base.BaseAdapter
import com.namshi.sharukh.base.BaseViewHolder
import com.namshi.sharukh.dataModels.Image
import com.namshi.sharukh.dataModels.NamshiWidget
import com.namshi.sharukh.databinding.ItemImageSubBinding
import com.namshi.sharukh.modules.common.ActionListener
import com.namshi.sharukh.utils.clearAndAddAll
import com.namshi.sharukh.utils.load
import com.namshi.sharukh.utils.onClick

/**
 * Takes a list of images and displays them in a column of full width of screen.
 * */
class ImageRowAdapter(private val listener: ActionListener) : BaseAdapter<ImageRowAdapter.Holder>() {

    private val items: MutableList<Image> = mutableListOf()
    private var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_image_sub, parent, false)).apply {
            itemView.onClick() {
                val item = items[adapterPosition]
                listener.onItemClick(item)
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val widget = items[position]
        holder.bind(widget)
    }

    override fun getItemCount() = count

    fun setData(data: NamshiWidget) {
        items.clearAndAddAll(data.images)
        count = if (data.cols > 0) data.cols else 0
        notifyDataSetChanged()
    }


    class Holder(itemView: View) : BaseViewHolder<Image>(itemView) {
        override fun bind(data: Image) {
            ItemImageSubBinding
                .bind(itemView)
                .apply {
                    staticImage load data
                }
        }

    }
}