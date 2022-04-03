package com.zoom_machine.api.services.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BasketResponse(
    val basket: List<Purchases>,
    val delivery: String,
    val id: String,
    val total: Float
)
