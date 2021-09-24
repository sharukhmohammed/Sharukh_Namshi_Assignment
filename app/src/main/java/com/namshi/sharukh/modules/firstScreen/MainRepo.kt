package com.namshi.sharukh.modules.firstScreen

import com.namshi.sharukh.models.NamshiWidget
import com.namshi.sharukh.network.NetworkRepo
import com.namshi.sharukh.network.response.Carousel
import com.namshi.sharukh.network.response.HomeContent
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber

class MainRepo {

    fun getMainScreenContent(): Observable<HomeContent> {
        return NetworkRepo.api().api1Content()
    }


    fun getCarouselData(widget: NamshiWidget): Observable<Carousel> {
        return NetworkRepo.api().getCarouselData(widget.url).map { it.url = widget.url;it }
    }


    fun getProductList(): Observable<Carousel> {
        return NetworkRepo.api().api2List()
    }

}