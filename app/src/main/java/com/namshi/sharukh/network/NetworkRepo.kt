package com.namshi.sharukh.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

object NetworkRepo {

    private const val BASE_URL = "https://demo8082631.mockable.io/"

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BASIC) })
        .build()

    @ExperimentalSerializationApi
    fun api(): Api {
        val contentType = "application/json".toMediaType()
        val retrofit = Retrofit.Builder()
            .client(okHttp)
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return retrofit.create(Api::class.java)
    }
}