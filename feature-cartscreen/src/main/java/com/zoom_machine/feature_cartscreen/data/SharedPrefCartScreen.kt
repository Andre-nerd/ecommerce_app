package com.zoom_machine.feature_cartscreen.data

interface SharedPrefCartScreen {
    fun saveCartScreenToSharedPref()
    fun refreshCartScreenToSharedPref()
    fun readCartScreenFromSharedPref(): Boolean
}