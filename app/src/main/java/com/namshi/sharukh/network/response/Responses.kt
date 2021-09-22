package com.namshi.sharukh.network.response

import com.namshi.sharukh.models.Image
import com.namshi.sharukh.models.NamshiWidget
import kotlinx.serialization.Serializable

/**
 * API 1 Response
 * */
@Serializable
data class ContentResponse(val content: List<NamshiWidget> = listOf())

@Serializable
data class CarouselResponse(val images: List<Image> = listOf())