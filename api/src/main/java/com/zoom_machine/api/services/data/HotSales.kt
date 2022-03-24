package com.zoom_machine.api.services.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HotSales(
    val id: Long,
    val is_new: Boolean?,
    val title: String,
    val subtitle: String,
    val picture: String,
    val is_buy: Boolean?
)
