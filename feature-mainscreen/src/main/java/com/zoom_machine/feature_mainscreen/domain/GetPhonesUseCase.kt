package com.zoom_machine.feature_mainscreen.domain

import com.zoom_machine.api.services.data.MainScreenResponse
import javax.inject.Inject

class GetPhonesUseCase @Inject constructor(private val mainScreenRepository: MainScreenRepository) {
    suspend fun getContentPhones(): MainScreenResponse {
        return mainScreenRepository.getContentPhones()
    }
}