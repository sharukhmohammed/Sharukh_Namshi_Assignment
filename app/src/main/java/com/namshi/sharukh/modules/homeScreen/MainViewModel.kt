package com.namshi.sharukh.modules.homeScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.namshi.sharukh.network.response.ApiResponse
import com.namshi.sharukh.network.response.CarouselContent
import com.namshi.sharukh.network.response.HomeContent
import com.namshi.sharukh.utils.plusAssign
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class MainViewModel : ViewModel() {


    private val homeContent: ApiResponse<HomeContent> = ApiResponse()
    val homeContentLiveData: MutableLiveData<ApiResponse<HomeContent>> = MutableLiveData(ApiResponse())

    private val productList: ApiResponse<CarouselContent> = ApiResponse()
    val productListLiveData: MutableLiveData<ApiResponse<CarouselContent>> = MutableLiveData(ApiResponse())

    private val model: MainModel = MainModel()
    private val subscriptions = CompositeDisposable()

    init {
        fetchInitialData()
    }

    fun refreshMainScreen() = fetchInitialData()
    fun refreshProductScreen() = getProductList()

    private fun fetchInitialData() {
        subscriptions += model.getMainScreenContent()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                homeContent.isLoading = true
                homeContentLiveData.postValue(homeContent)
            }
            .subscribe({
                homeContent.isLoading = false
                homeContent.data = it
                homeContent.exception = null
                homeContentLiveData.postValue(homeContent)
            }, {
                Timber.e(it)
                homeContent.isLoading = false
                homeContent.exception = it as Exception?
                homeContentLiveData.postValue(homeContent)
            })
    }

    fun getProductList() {
        subscriptions += model.getProductList()
            .doOnSubscribe {
                productList.isLoading = true
                productListLiveData.postValue(productList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                productList.isLoading = false
                productList.data = it
                productListLiveData.postValue(productList)
            }, {
                Timber.e(it)
                productList.isLoading = false
                productList.exception = it as Exception?
                productListLiveData.postValue(productList)
            })
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}