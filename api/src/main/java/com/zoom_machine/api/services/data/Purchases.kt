package com.zoom_machine.api.services.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Purchases(
    val id: Long,
    val images: String,
    val price: Float,
    val title: String,
    var count: Int = 1
)
