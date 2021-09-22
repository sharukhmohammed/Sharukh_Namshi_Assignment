package com.namshi.sharukh.models

import com.namshi.sharukh.utils.Constant
import kotlinx.serialization.Serializable

@Serializable
data class NamshiWidget(
    val type: Type = Type.unknown,
    val cols: Int = -1,
    val show: Int = -1, //Carousel or Slider
    val images: MutableList<Image> = mutableListOf(),
    val title: String = Constant.EMPTY_STR, //Carousel Title
    val height: Int = -1, //Carousel Height
    val url: String = Constant.EMPTY_STR, //Carousel API URL
) {
    @Serializable
    enum class Type { image, carousel, slider, unknown }
}