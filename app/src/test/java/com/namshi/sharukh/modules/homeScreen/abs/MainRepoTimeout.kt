package com.namshi.sharukh.modules.homeScreen.abs

import com.namshi.sharukh.dataModels.NamshiWidget
import com.namshi.sharukh.network.response.CarouselContent
import com.namshi.sharukh.network.response.HomeContent
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class MainRepoTimeout : MainRepoAbs() {

    override fun getMainScreenContent(): Observable<HomeContent> {
        return super.getMainScreenContent().delay(5, TimeUnit.SECONDS)
    }

    override fun getCarouselData(widget: NamshiWidget): Observable<CarouselContent> {
        val carousel = NamshiWidget(NamshiWidget.Type.carousel, url = "http://demo8082631.mockable.io/brands")
        return super.getCarouselData(carousel).delay(5, TimeUnit.SECONDS)
    }

    override fun getProductList(): Observable<CarouselContent> {
        return super.getProductList().delay(5, TimeUnit.SECONDS)
    }
}