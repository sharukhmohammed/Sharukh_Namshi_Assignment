package com.namshi.sharukh.modules.homeScreen

import com.namshi.sharukh.dataModels.NamshiWidget
import com.namshi.sharukh.modules.homeScreen.abs.MainRepoAbs
import com.namshi.sharukh.network.response.CarouselContent
import com.namshi.sharukh.network.response.HomeContent
import io.reactivex.rxjava3.core.Observable

class MainRepo : MainRepoAbs() {
    companion object {
        const val TIMEOUT = 3L
    }

    override fun getCarouselData(widget: NamshiWidget): Observable<CarouselContent> {
        return super.getCarouselData(widget).applyTimeout()
    }

    override fun getMainScreenContent(): Observable<HomeContent> {
        return super.getMainScreenContent().applyTimeout()
    }

    override fun getProductList(): Observable<CarouselContent> {
        return super.getProductList().applyTimeout()
    }

}