package com.namshi.sharukh.modules.homeScreen.abs

import com.google.common.truth.Truth.assertThat
import com.namshi.sharukh.dataModels.NamshiWidget
import org.junit.Before
import org.junit.Test
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class MainRepoTest {

    lateinit var mainRepoTimeout: MainRepoTimeout
    lateinit var mainRepoNoNetwork: MainRepoNoNetwork

    @Before
    fun setUp() {
        mainRepoTimeout = MainRepoTimeout()
        mainRepoNoNetwork = MainRepoNoNetwork()
    }

    @Test
    fun mainScreenTimeout_returnsTimeoutException() {
        try {
            mainRepoTimeout.getMainScreenContent().timeout(1, TimeUnit.SECONDS)
        } catch (e: Exception) {
            assertThat(e is TimeoutException)
        }
    }

    @Test
    fun carouselDataTimeout_returnsTimeoutException() {
        try {
            mainRepoTimeout.getCarouselData(NamshiWidget()).timeout(1, TimeUnit.SECONDS)
        } catch (e: Exception) {
            assertThat(e is TimeoutException)
        }
    }


    @Test
    fun productListTimeout_returnsTimeoutException() {
        try {
            mainRepoTimeout.getProductList().timeout(1, TimeUnit.SECONDS)
        } catch (e: Exception) {
            assertThat(e is TimeoutException)
        }
    }


    @Test
    fun mainScreenNoNetwork_returnsNoNetworkException() {
        try {
            mainRepoNoNetwork.getMainScreenContent().timeout(1, TimeUnit.SECONDS)
        } catch (e: Exception) {
            assertThat(e is SocketTimeoutException)
        }
    }

    @Test
    fun carouselDataNoNetwork_returnsNoNetworkException() {
        try {
            mainRepoNoNetwork.getCarouselData(NamshiWidget()).timeout(1, TimeUnit.SECONDS)
        } catch (e: Exception) {
            assertThat(e is SocketTimeoutException)
        }
    }


    @Test
    fun productListNoNetwork_returnsNoNetworkException() {
        try {
            mainRepoNoNetwork.getProductList().timeout(1, TimeUnit.SECONDS)
        } catch (e: Exception) {
            assertThat(e is SocketTimeoutException)
        }
    }
}