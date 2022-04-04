package com.zoom_machine.feature_mainscreen.domain

import com.zoom_machine.feature_mainscreen.data.SharedPrefMainScreen
import javax.inject.Inject

class SharedPrefMainScreenUseCase @Inject constructor(
    private val sharedPrefMainScreen: SharedPrefMainScreen
) {
    fun isFirstLaunch(): Boolean {
        return sharedPrefMainScreen.readDataMainScreenFromSharedPref()
    }

    fun mainScreenNoFirstLaunch() {
        sharedPrefMainScreen.saveDataMainScreenToSharedPref()
    }

    fun refreshSharedPref() {
        sharedPrefMainScreen.refreshDataMainScreenToSharedPref()
    }
}