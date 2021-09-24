package com.namshi.sharukh.modules.firstScreen.abs

import com.namshi.sharukh.models.NamshiWidget
import com.namshi.sharukh.network.response.Carousel
import com.namshi.sharukh.network.response.HomeContent
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class MainRepoTimeout : MainRepoAbs() {

    override fun getMainScreenContent(): Observable<HomeContent> {
        return super.getMainScreenContent().delay(5, TimeUnit.SECONDS)
    }

    override fun getCarouselData(widget: NamshiWidget): Observable<Carousel> {
        val carousel = NamshiWidget(NamshiWidget.Type.carousel, url = "http://demo8082631.mockable.io/brands")
        return super.getCarouselData(carousel).delay(5, TimeUnit.SECONDS)
    }

    override fun getProductList(): Observable<Carousel> {
        return super.getProductList().delay(5, TimeUnit.SECONDS)
    }
}