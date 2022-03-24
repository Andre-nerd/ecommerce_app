package com.zoom_machine.api.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttp {
    val client = OkHttpClient.Builder()
        .addNetworkInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()
}