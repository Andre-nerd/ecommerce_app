package com.zoom_machine.api.services

import com.zoom_machine.api.services.data.MainScreenResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface MainScreenService {
    @GET("/home")
    suspend fun getContentForMainScreen(): MainScreenResponse
}

fun mainScreenService(): MainScreenService {
    val okHttpClient = OkHttp.client
    val retrofit = Retrofit.Builder()
        .baseUrl("https://c7ae1634-c91d-4a58-8aa4-8aef21936a9d.mock.pstmn.io")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(MainScreenService::class.java)
}