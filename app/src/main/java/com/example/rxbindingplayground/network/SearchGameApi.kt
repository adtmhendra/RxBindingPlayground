package com.example.rxbindingplayground.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object SearchGameApi {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val retrofit = Retrofit.Builder().apply {
        baseUrl(SearchGameService.BASE_URL)
        addConverterFactory(MoshiConverterFactory.create(moshi))
        addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    }.build()

    val retrofitService: SearchGameService by lazy { retrofit.create(SearchGameService::class.java) }
}