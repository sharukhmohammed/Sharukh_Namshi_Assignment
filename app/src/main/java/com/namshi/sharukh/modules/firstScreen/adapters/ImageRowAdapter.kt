package com.namshi.sharukh.modules.firstScreen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.namshi.sharukh.R
import com.namshi.sharukh.base.BaseAdapter
import com.namshi.sharukh.base.BaseViewHolder
import com.namshi.sharukh.databinding.ItemImageSubBinding
import com.namshi.sharukh.models.Image
import com.namshi.sharukh.models.NamshiWidget
import com.namshi.sharukh.utils.clearAndAddAll
import com.namshi.sharukh.utils.load

class ImageRowAdapter : BaseAdapter<ImageRowAdapter.Holder>() {

    private val items: MutableList<Image> = mutableListOf()
    private var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_image_sub, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val widget = items[position]
        holder.bind(widget)
    }

    override fun getItemCount() = count

    fun setData(data: NamshiWidget) {
        items.clearAndAddAll(data.images)
        count = if (data.cols == -1) 0 else data.cols
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