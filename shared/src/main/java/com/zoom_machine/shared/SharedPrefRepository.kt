package com.zoom_machine.shared

interface SharedPrefRepository {
    fun saveFirstLaunchToSharedPref()
    fun readFirstLaunchFromSharedPref():Boolean
}