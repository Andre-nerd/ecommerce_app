package com.zoom_machine.ecommerce_app.domain

import com.zoom_machine.ecommerce_app.data.MainScreenResponse
import com.zoom_machine.ecommerce_app.presentation.ui.ui_components.TopMenuItem

interface MainScreenRepository {

    suspend fun getContentPhones(): MainScreenResponse

    fun getItemsTopMenu(): List<TopMenuItem>
}