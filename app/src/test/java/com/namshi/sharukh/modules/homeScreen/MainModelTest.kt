package com.namshi.sharukh.modules.homeScreen

import com.google.common.truth.Truth.assertThat
import com.namshi.sharukh.dataModels.NamshiWidget
import com.namshi.sharukh.network.response.CarouselContent
import org.junit.Test

class MainModelTest {

    private val model = MainModel()

    /**
     * Check if all the carousels (which are to be lazy loaded)
     * are prefilled by the time final emitted arrives from the model
     * */
    @Test
    fun mainScreenCarouselsParallelFetch_returnsTrue() {
        val originalRequest = model.getMainScreenContent()
        val result = originalRequest.blockingLast().content
        assertThat(result.filter { it.type == NamshiWidget.Type.carousel }.none { it.url.isEmpty() && it.images.isEmpty() }).isTrue()
    }


    @Test
    fun productListFetchingSuccess_returnsTrue() {
        val originalRequest = model.getProductList()
        val result = originalRequest.blockingLast().images
        assertThat(result).isNotNull()
    }

    @Test
    fun productListFetchSuccess_returnsTrue() {
        val originalRequest = model.getProductList()
        val result = originalRequest.blockingLast().images
        assertThat(result).isNotNull()
    }

    @Test
    fun productListDataResponseEmpty_returnsTrue() {
        val originalRequest = model.getProductList()
        val result = originalRequest.map { CarouselContent() }.blockingLast().images
        assertThat(result).isEmpty()
    }
}