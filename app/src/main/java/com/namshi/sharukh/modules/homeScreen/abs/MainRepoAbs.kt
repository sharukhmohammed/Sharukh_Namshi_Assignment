package com.namshi.sharukh.modules.homeScreen.abs

import com.namshi.sharukh.dataModels.NamshiWidget
import com.namshi.sharukh.modules.homeScreen.MainRepo
import com.namshi.sharukh.network.NetworkRepo
import com.namshi.sharukh.network.response.Carousel
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
        return NetworkRepo.api().api1Content()
    }


    open fun getCarouselData(widget: NamshiWidget): Observable<Carousel> {
        return NetworkRepo.api()
            .getCarouselData(widget.url)
            .map { it.url = widget.url;it }
    }


    open fun getProductList(): Observable<Carousel> {
        return NetworkRepo.api().api2List()
    }

}