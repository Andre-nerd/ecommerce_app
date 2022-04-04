package com.zoom_machine.feature_mainscreen.data

interface SharedPrefMainScreen {
    fun saveDataMainScreenToSharedPref()
    fun refreshDataMainScreenToSharedPref()
    fun readDataMainScreenFromSharedPref(): Boolean
}