package com.zoom_machine.feature_mainscreen.domain

import com.zoom_machine.api.services.data.HotSales
import com.zoom_machine.api.services.data.MainScreenResponse

interface MainScreenRepository {

    suspend fun getContentPhones(): MainScreenResponse

    suspend fun saveHotSalesToDatabase(items:List<HotSales>)

}