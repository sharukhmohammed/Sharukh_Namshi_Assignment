package com.namshi.sharukh.network.response

import com.namshi.sharukh.dataModels.Image
import com.namshi.sharukh.dataModels.NamshiWidget
import com.namshi.sharukh.utils.Constant
import kotlinx.serialization.Serializable


class ApiResponse<T> {
    var isLoading: Boolean = false
    var exception: Exception? = null
    var data: T? = null
}


/**
 * API 1 Response
 * */
@Serializable
data class HomeContent(val content: List<NamshiWidget> = listOf())

/**
 * API 2, 3, 4 Response
* */
@Serializable
data class Carousel(val images: List<Image> = listOf(), var url: String = Constant.EMPTY_STR)