package com.namshi.sharukh.modules.firstScreen

import com.namshi.sharukh.models.NamshiWidget
import com.namshi.sharukh.network.NetworkRepo
import com.namshi.sharukh.network.response.CarouselResponse
import com.namshi.sharukh.network.response.ContentResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class MainModel {

    fun getMainScreenContent(): Observable<ContentResponse> {
        return NetworkRepo.api().api1Content()
    }


    fun getCarouselData(carousel: NamshiWidget): Observable<CarouselResponse> {
        return NetworkRepo.api().getCarouselData(carousel.url)
    }

}