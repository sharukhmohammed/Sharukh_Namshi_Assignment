package com.namshi.sharukh.modules.homeScreen

import com.google.common.truth.Truth.assertThat
import com.namshi.sharukh.dataModels.NamshiWidget
import org.junit.Test

class MainModelTest {

    private val model = MainModel()

    /**
     * Check if all the carousels (which are to be lazy loaded)
     * are prefilled by the time final emitted arrives from the model
     * */
    @Test
    fun carouselsContainDataAsync() {

        val originalRequest = model.getMainScreenContent()

        val result = originalRequest.blockingLast().content

        assertThat(result.filter { it.type == NamshiWidget.Type.carousel }.none { it.url.isEmpty() && it.images.isEmpty() }).isTrue()

    }
}