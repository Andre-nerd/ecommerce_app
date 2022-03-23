package com.zoom_machine.api.services.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BestSeller(
    val id: Long,
    val is_favorites: Boolean?,
    val title: String,
    val price_without_discount: Int,
    val discount_price: Int,
    val picture: String
)