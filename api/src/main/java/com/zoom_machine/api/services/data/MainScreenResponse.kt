package com.zoom_machine.api.services.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import javax.inject.Inject

@JsonClass(generateAdapter = true)
data class MainScreenResponse @Inject constructor(
    val _id: String,
    @Json(name = "home_store")
    val hotSales: List<HotSales>,
    @Json(name = "best_seller")
    val bestSeller: List<BestSeller>
)

