package com.zoom_machine.feature_detailsscreen.presentation.utils

const val NO_INFO = "No info"
const val GB = " GB"
const val FIRST_CAPACITY = 0
const val SECOND_CAPACITY = 1
const val FIRST_COLOR = 0
const val SECOND_COLOR = 1


fun String.formatFloat(value:Float):String{
        val th = value%1000
        val grb = value - th
        return th.toString()+","+String.format("%.2f",grb)
}
