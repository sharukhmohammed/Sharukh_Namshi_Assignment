package com.namshi.sharukh.network

import com.namshi.sharukh.network.response.Carousel
import com.namshi.sharukh.network.response.HomeContent
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Url


interface Api {

    @GET("content")
    fun api1Content() : Observable<HomeContent>

    @GET("list")
    fun api2List() : Observable<Carousel>

    @GET
    fun getCarouselData(@Url url: String): Observable<Carousel>
}