package com.zoom_machine.feature_mainscreen.domain

import com.zoom_machine.api.services.data.MainScreenResponse

interface MainScreenRepository {

    suspend fun getContentPhones(): MainScreenResponse

}