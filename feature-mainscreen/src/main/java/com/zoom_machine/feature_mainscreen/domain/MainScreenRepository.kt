package com.zoom_machine.feature_mainscreen.domain

import com.zoom_machine.api.services.data.MainScreenResponse
import com.zoom_machine.feature_mainscreen.presentation.ui.ui_components.TopMenuItem

interface MainScreenRepository {

    suspend fun getContentPhones(): MainScreenResponse

}