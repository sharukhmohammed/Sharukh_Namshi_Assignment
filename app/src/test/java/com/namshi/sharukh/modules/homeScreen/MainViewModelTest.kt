package com.namshi.sharukh.modules.homeScreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.namshi.sharukh.rules.RxImmediateSchedulerRule
import kotlinx.serialization.SerializationException
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainViewModelTest {

    private lateinit var viewModel: MainViewModel


    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()


    @Before
    fun setup() {
        viewModel = MainViewModel()
    }


    @Test
    fun homeScreenDataFetch_returnsTrue() {
        viewModel.refreshMainScreen()
        Thread.sleep(3000)
        val response = viewModel.homeContentLiveData.value
        assertThat(response?.data).isNotNull()
    }

    @Test
    fun homeScreenDataFetchErrorCheck_returnsTrue() {
        viewModel.refreshMainScreen()
        val response = viewModel.homeContentLiveData.value
        response?.exception = SerializationException("JSON parse failed")
        assertThat(response?.exception).isNotNull()
    }

    @Test
    fun homeScreenDataFetchStartsLoading_returnsTrue() {
        viewModel.refreshMainScreen()
        val response = viewModel.homeContentLiveData.value
        assertThat(response?.isLoading).isTrue()
    }

    @Test
    fun homeScreenDataFetchInitialDataIsNull_returnsTrue() {
        viewModel.refreshMainScreen()
        val response = viewModel.homeContentLiveData.value
        assertThat(response?.data).isNull()
    }


    @Test
    fun productDataFetch_returnsTrue() {
        viewModel.refreshProductScreen()
        Thread.sleep(3000)
        val response = viewModel.productListLiveData.value
        assertThat(response?.data).isNotNull()
    }

    @Test
    fun productDataFetchErrorCheck_returnsTrue() {
        viewModel.refreshProductScreen()
        val response = viewModel.productListLiveData.value
        response?.exception = SerializationException("JSON parse failed")
        assertThat(response?.exception).isNotNull()
    }

    @Test
    fun productDataFetchStartsLoading_returnsTrue() {
        viewModel.refreshProductScreen()
        val response = viewModel.productListLiveData.value
        assertThat(response?.isLoading).isTrue()
    }

    @Test
    fun productDataFetchInitialDataIsNull_returnsTrue() {
        viewModel.refreshProductScreen()
        val response = viewModel.productListLiveData.value
        assertThat(response?.data).isNull()
    }

}