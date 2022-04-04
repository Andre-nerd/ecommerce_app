package com.zoom_machine.feature_detailsscreen.data

interface SharedPrefDetailsScreen {
    fun saveDetailsScreenToSharedPref()
    fun refreshDetailsScreenToSharedPref()
    fun readDetailsScreenFromSharedPref():Boolean
}