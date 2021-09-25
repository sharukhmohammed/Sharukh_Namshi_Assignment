package com.namshi.sharukh.modules.homeScreen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.namshi.sharukh.R
import com.namshi.sharukh.base.BaseAdapter
import com.namshi.sharukh.base.BaseFragment
import com.namshi.sharukh.dataModels.NamshiWidget
import com.namshi.sharukh.databinding.ItemCarouselBinding
import com.namshi.sharukh.databinding.ItemImageBinding
import com.namshi.sharukh.databinding.ItemSliderBinding
import com.namshi.sharukh.modules.common.ActionListener
import com.namshi.sharukh.utils.clearAndAddAll
import com.namshi.sharukh.utils.gone

/**
 * Main Screen Adapter that identifies the type of [NamshiWidget] and assigns the appropriate view and adapters based on respective [NamshiWidget.Type]
 * */
class NamshiWidgetAdapter(private val fragment: BaseFragment, private val listener: ActionListener) : BaseAdapter<NamshiWidgetAdapter.Holder>(fragment) {

    private val items: MutableList<NamshiWidget> = mutableListOf()

    private val imageViewPool = RecyclerView.RecycledViewPool()
    private val carouselViewPool = RecyclerView.RecycledViewPool()
    private val sliderViewPool = RecyclerView.RecycledViewPool()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val holder = when (viewType) {
            NamshiWidget.Type.image.asInt -> Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))
            NamshiWidget.Type.slider.asInt -> Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_slider, parent, false))
            NamshiWidget.Type.carousel.asInt -> Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false))
            else -> Holder(View(parent.context))
        }
        return holder
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val widget = items[holder.adapterPosition]
        when (holder.itemViewType) {
            NamshiWidget.Type.image.asInt -> {
                ItemImageBinding.bind(holder.itemView).apply {
                    imageRecycler.apply {
                        setRecycledViewPool(imageViewPool)
                        layoutManager = GridLayoutManager(context, widget.cols)
                        adapter = ImageRowAdapter(listener).apply { setData(widget) }
                    }
                }
            }
            NamshiWidget.Type.slider.asInt -> {
                ItemSliderBinding.bind(holder.itemView).apply {
                    sliderRecycler.apply {
                        setRecycledViewPool(sliderViewPool)
                        if (onFlingListener == null)
                            PagerSnapHelper().attachToRecyclerView(this)
                        adapter = SliderAdapter(fragment, listener).apply { setData(widget.images) }
                    }
                }
            }
            NamshiWidget.Type.carousel.asInt -> {
                ItemCarouselBinding.bind(holder.itemView).apply {

                    root.layoutParams.apply {
                        height = widget.heightPx
                    }

                    if (widget.images.isEmpty()) {
                        carouselLoading.show()
                    } else {
                        carouselLoading.gone()
                        carouselTitle.text = widget.title
                        carouselRecycler.setRecycledViewPool(carouselViewPool)
                        carouselRecycler.adapter = CarouselAdapter(listener).apply { setData(widget.images) }
                    }
                }
            }
            else -> Unit
        }
    }

    override fun getItemViewType(position: Int) = items[position].type.asInt
    override fun getItemCount() = items.size


    fun setData(data: List<NamshiWidget>) {
        items.clearAndAddAll(data)
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}