package com.namshi.sharukh.network

import com.namshi.sharukh.network.response.CarouselContent
import com.namshi.sharukh.network.response.HomeContent
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Behaviour of Mockable.io API
* */
interface Api {

    @GET("content")
    fun api1Content() : Observable<HomeContent>

    @GET("list")
    fun api2List() : Observable<CarouselContent>

    @GET
    fun getCarouselData(@Url url: String): Observable<CarouselContent>
}