package com.zoom_machine.feature_mainscreen.presentation.ui.ui_components

import androidx.annotation.DrawableRes

data class TopMenuItem(
    @DrawableRes
    val icon: Int,
    val title: Int,
    var isSelected: Boolean = false
)
