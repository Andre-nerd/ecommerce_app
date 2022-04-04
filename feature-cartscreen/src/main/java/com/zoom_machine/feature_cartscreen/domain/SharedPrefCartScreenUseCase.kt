package com.zoom_machine.feature_cartscreen.domain

import com.zoom_machine.feature_cartscreen.data.SharedPrefCartScreen
import javax.inject.Inject

class SharedPrefCartScreenUseCase @Inject constructor(
    private val sharedPrefCartsScreen: SharedPrefCartScreen
) {
    fun isFirstLaunch(): Boolean {
        return sharedPrefCartsScreen.readCartScreenFromSharedPref()
    }

    fun cartScreenNoFirstLaunch() {
        sharedPrefCartsScreen.saveCartScreenToSharedPref()
    }

    fun refreshSharedPref() {
        sharedPrefCartsScreen.refreshCartScreenToSharedPref()
    }
}