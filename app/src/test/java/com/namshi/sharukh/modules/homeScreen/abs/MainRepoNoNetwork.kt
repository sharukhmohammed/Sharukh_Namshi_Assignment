package com.namshi.sharukh.modules.homeScreen.abs

import com.namshi.sharukh.dataModels.NamshiWidget
import com.namshi.sharukh.network.response.Carousel
import com.namshi.sharukh.network.response.HomeContent
import io.reactivex.rxjava3.core.Observable
import java.net.SocketTimeoutException

class MainRepoNoNetwork : MainRepoAbs() {

    override fun getMainScreenContent(): Observable<HomeContent> {
        return super.getMainScreenContent().flatMap { Observable.error<HomeContent>(SocketTimeoutException()) }
    }

    override fun getCarouselData(widget: NamshiWidget): Observable<Carousel> {
        val carousel = NamshiWidget(NamshiWidget.Type.carousel, url = "http://demo8082631.mockable.io/brands")
        return super.getCarouselData(carousel).flatMap { Observable.error<Carousel>(SocketTimeoutException()) }
    }

    override fun getProductList(): Observable<Carousel> {
        return super.getProductList().flatMap { Observable.error<Carousel>(SocketTimeoutException()) }
    }
}