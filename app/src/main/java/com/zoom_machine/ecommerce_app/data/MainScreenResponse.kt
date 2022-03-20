package com.zoom_machine.ecommerce_app.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MainScreenResponse(
    val _id: String,
    @Json(name = "home_store")
    val hotSales: List<HotSales>,
    @Json(name = "best_seller")
    val bestSeller: List<BestSeller>
)

