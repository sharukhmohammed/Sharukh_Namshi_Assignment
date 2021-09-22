package com.namshi.sharukh.network

import com.namshi.sharukh.network.response.CarouselResponse
import com.namshi.sharukh.network.response.ContentResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Url


interface Api {

    @GET("content")
    fun api1Content() : Observable<ContentResponse>

    @GET
    fun getCarouselData(@Url url: String): Observable<CarouselResponse>
}