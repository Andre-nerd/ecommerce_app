package com.zoom_machine.ecommerce_app.presentation.data

import androidx.annotation.DrawableRes

data class TopMenuItem(
    @DrawableRes
    val icon: Int,
    val title:Int,
    var isSelected: Boolean = false
)
