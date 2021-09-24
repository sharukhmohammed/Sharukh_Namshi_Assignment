package com.namshi.sharukh.modules.firstScreen

import com.namshi.sharukh.models.NamshiWidget
import com.namshi.sharukh.network.NetworkRepo
import com.namshi.sharukh.network.response.Carousel
import com.namshi.sharukh.network.response.HomeContent
import io.reactivex.rxjava3.core.Observable

class MainModel {

    fun getMainScreenContent(): Observable<HomeContent> {
        return NetworkRepo.api().api1Content()
    }


    fun getCarouselData(carousel: NamshiWidget): Observable<Carousel> {
        return NetworkRepo.api().getCarouselData(carousel.url)
    }


    fun getProductList(): Observable<Carousel> {
        return NetworkRepo.api().api2List()
    }

}