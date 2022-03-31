package com.zoom_machine.feature_cartscreen.presentation.utils

fun formatFloat(value: Float): String {
    val s = value.toString()
    val th = value % 1000f
    return s[0] + " " + String.format("%.2f", th)
}