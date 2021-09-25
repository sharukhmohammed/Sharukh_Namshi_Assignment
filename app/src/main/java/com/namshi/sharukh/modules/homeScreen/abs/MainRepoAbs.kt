package com.namshi.sharukh.modules.homeScreen.abs

import com.namshi.sharukh.dataModels.NamshiWidget
import com.namshi.sharukh.modules.homeScreen.MainRepo
import com.namshi.sharukh.network.NetworkClient
import com.namshi.sharukh.network.response.CarouselContent
import com.namshi.sharukh.network.response.HomeContent
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

/**
 * Main Repo where it defines what data it provides of the Home screen, product list.
* */
abstract class MainRepoAbs {

    companion object {
        private const val TIMEOUT = 3L
        fun <T> Observable<T>.applyTimeout(): Observable<T> {
            return this.timeout(TIMEOUT, TimeUnit.SECONDS)
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