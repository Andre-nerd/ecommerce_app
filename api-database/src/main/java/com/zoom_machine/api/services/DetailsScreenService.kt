package com.zoom_machine.api.services

import com.zoom_machine.api.services.data.ProductDetails
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface DetailsScreenService {
    @GET("6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getContentForMainScreen(): ProductDetails
}

fun detailsScreenService(): DetailsScreenService {
    val okHttpClient = OkHttp.client
    val retrofit = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/v3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(DetailsScreenService::class.java)
}