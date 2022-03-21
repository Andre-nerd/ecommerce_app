package com.zoom_machine.ecommerce_app.data.networking

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class RetrofitApi @Inject constructor(okHttpClient: OkHttpClient) {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://shopapi-0575.restdb.io")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient.client)
        .build()

    val apiMainScreen: ApiMainScreen
        get() = retrofit.create(ApiMainScreen::class.java)
}