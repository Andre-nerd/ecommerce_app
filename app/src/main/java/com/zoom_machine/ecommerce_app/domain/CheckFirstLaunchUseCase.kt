package com.zoom_machine.ecommerce_app.domain

import com.zoom_machine.shared.SharedPrefRepository
import javax.inject.Inject

class CheckFirstLaunchUseCase @Inject constructor(private val repository: com.zoom_machine.shared.SharedPrefRepository) {
    fun checkIsFirstLaunch(): Boolean {
        return repository.readFirstLaunchFromSharedPref()
    }
}