package com.zoom_machine.feature_detailsscreen.domain

import com.zoom_machine.feature_detailsscreen.data.SharedPrefDetailsScreen
import javax.inject.Inject

class SharedPrefDetailsScreenUseCase @Inject constructor(
    private val sharedPrefMainScreen: SharedPrefDetailsScreen
) {
    fun isFirstLaunch(): Boolean {
        return sharedPrefMainScreen.readDetailsScreenFromSharedPref()
    }

    fun mainScreenNoFirstLaunch() {
        sharedPrefMainScreen.saveDetailsScreenToSharedPref()
    }

    fun refreshSharedPref() {
        sharedPrefMainScreen.refreshDetailsScreenToSharedPref()
    }
}