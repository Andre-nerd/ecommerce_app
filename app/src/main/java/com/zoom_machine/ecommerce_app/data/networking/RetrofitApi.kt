package com.zoom_machine.ecommerce_app.data.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitApi() {
    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://shopapi-0575.restdb.io")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiMainScreen: ApiMainScreen
        get() = retrofit.create(ApiMainScreen::class.java)
}