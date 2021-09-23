package com.namshi.sharukh.models

import com.namshi.sharukh.utils.Constant
import com.namshi.sharukh.utils.dpToPx
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val url: String = Constant.EMPTY_STR,
    val width: Int = 0,
    val height: Int = 0,
    val format: Format = Format.unknown
) {

    val widthPx: Int
        get() = width.toFloat().dpToPx

    val heightPx: Int
        get() = height.toFloat().dpToPx

    @Serializable
    enum class Format { gif, image, unknown }
}