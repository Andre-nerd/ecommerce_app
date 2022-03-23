package com.zoom_machine.api.services

import com.zoom_machine.api.services.domain.MainScreenResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface MainScreenService {
    @GET("/rest/home")
    @Headers("x-apikey:61ddae2e95cb716ea5ee48e4")
    suspend fun getContentForMainScreen(): List<MainScreenResponse>
}

fun mainScreenService(): MainScreenService {
    val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://shopapi-0575.restdb.io")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(MainScreenService::class.java)
}