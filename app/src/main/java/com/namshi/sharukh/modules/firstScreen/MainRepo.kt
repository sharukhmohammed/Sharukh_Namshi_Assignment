package com.namshi.sharukh.modules.firstScreen

import com.namshi.sharukh.models.NamshiWidget
import com.namshi.sharukh.modules.firstScreen.abs.MainRepoAbs
import com.namshi.sharukh.network.response.Carousel
import com.namshi.sharukh.network.response.HomeContent
import io.reactivex.rxjava3.core.Observable

class MainRepo : MainRepoAbs() {
    companion object {
        const val TIMEOUT = 3L
    }

    override fun getCarouselData(widget: NamshiWidget): Observable<Carousel> {
        return super.getCarouselData(widget).applyTimeout()
    }

    override fun getMainScreenContent(): Observable<HomeContent> {
        return super.getMainScreenContent().applyTimeout()
    }

    override fun getProductList(): Observable<Carousel> {
        return super.getProductList().applyTimeout()
    }

}