package com.zoom_machine.feature_mainscreen.presentation.ui.ui_components

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

data class TopMenuItem(
    val icon: Drawable?,
    val title: String,
    var isSelected: Boolean = false
)
