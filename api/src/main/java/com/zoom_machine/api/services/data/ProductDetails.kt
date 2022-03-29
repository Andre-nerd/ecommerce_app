package com.zoom_machine.api.services.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDetails(
    val CPU: String,
    val camera:String,
    val capacity:List<String>,
    val color: List<String>,
    val id: String,
    val images: List<String>,
    var isFavorites: Boolean?,
    val price:Int,
    val rating:Float,
    val sd:String,
    val ssd: String,
    val title:String
)

