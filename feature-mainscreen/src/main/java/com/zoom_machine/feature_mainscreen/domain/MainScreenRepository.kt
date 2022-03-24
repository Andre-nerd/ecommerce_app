package com.zoom_machine.feature_mainscreen.domain

import com.zoom_machine.api.services.MainScreenService
import com.zoom_machine.api.services.domain.MainScreenResponse
import com.zoom_machine.feature_mainscreen.presentation.ui.ui_components.TopMenuItem

interface MainScreenRepository {

    suspend fun getContentPhones(): MainScreenResponse

    fun getItemsTopMenu(): List<TopMenuItem>
}