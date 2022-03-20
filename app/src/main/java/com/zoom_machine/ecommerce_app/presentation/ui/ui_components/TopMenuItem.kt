package com.zoom_machine.ecommerce_app.presentation.ui.ui_components

import androidx.annotation.DrawableRes

data class TopMenuItem(
    @DrawableRes
    val icon: Int,
    val title: Int,
    var isSelected: Boolean = false
)
