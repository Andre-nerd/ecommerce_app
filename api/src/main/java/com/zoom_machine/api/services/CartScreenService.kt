package com.zoom_machine.api.services

import com.zoom_machine.api.services.data.BasketResponse
import com.zoom_machine.api.services.data.Purchases
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface CartScreenService {
    @GET("53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getContentForCartScreen(): BasketResponse
}

fun cartScreenService(): CartScreenService {
    val okHttpClient = OkHttp.client
    val retrofit = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/v3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(CartScreenService::class.java)
}
