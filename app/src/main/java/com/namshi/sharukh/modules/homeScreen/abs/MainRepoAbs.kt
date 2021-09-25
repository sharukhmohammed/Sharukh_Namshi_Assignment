package com.namshi.sharukh.modules.homeScreen.abs

import com.namshi.sharukh.dataModels.NamshiWidget
import com.namshi.sharukh.modules.homeScreen.MainRepo
import com.namshi.sharukh.network.NetworkClient
import com.namshi.sharukh.network.response.CarouselContent
import com.namshi.sharukh.network.response.HomeContent
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

abstract class MainRepoAbs {

    companion object {
        fun <T> Observable<T>.applyTimeout(): Observable<T> {
            return this.timeout(MainRepo.TIMEOUT, TimeUnit.SECONDS)
        }
    }

    open fun getMainScreenContent(): Observable<HomeContent> {
        return NetworkClient.api().api1Content()
    }


    open fun getCarouselData(widget: NamshiWidget): Observable<CarouselContent> {
        return NetworkClient.api()
            .getCarouselData(widget.url)
            .map { it.url = widget.url;it }
    }


    open fun getProductList(): Observable<CarouselContent> {
        return NetworkClient.api().api2List()
    }

}