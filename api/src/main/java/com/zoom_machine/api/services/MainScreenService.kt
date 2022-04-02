package com.zoom_machine.api.services

import com.zoom_machine.api.services.data.MainScreenResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface MainScreenService {
    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getContentForMainScreen(): MainScreenResponse
}

fun mainScreenService(): MainScreenService {
    val okHttpClient = OkHttp.client
    val retrofit = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/v3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(MainScreenService::class.java)
}