package com.zoom_machine.ecommerce_app.data.networking

import com.zoom_machine.ecommerce_app.data.MainScreenResponse
import retrofit2.http.GET
import retrofit2.http.Headers


interface ApiMainScreen {

    @GET("/rest/home")
    @Headers("x-apikey:61ddae2e95cb716ea5ee48e4")
    suspend fun getContentForMainScreen(): List<MainScreenResponse>
}


