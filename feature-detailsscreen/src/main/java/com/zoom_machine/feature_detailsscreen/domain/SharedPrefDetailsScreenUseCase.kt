package com.zoom_machine.feature_detailsscreen.domain

import com.zoom_machine.feature_detailsscreen.data.SharedPrefDetailsScreen
import javax.inject.Inject

class SharedPrefDetailsScreenUseCase @Inject constructor(
    private val sharedPrefDetailsScreen: SharedPrefDetailsScreen
) {
    fun isFirstLaunch(): Boolean {
        return sharedPrefDetailsScreen.readDetailsScreenFromSharedPref()
    }

    fun detailScreenNoFirstLaunch() {
        sharedPrefDetailsScreen.saveDetailsScreenToSharedPref()
    }

    fun refreshSharedPref() {
        sharedPrefDetailsScreen.refreshDetailsScreenToSharedPref()
    }
}