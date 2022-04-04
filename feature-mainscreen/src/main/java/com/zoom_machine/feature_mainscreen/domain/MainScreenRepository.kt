package com.zoom_machine.feature_mainscreen.domain

import com.zoom_machine.api.services.data.BestSeller
import com.zoom_machine.api.services.data.HotSales
import com.zoom_machine.api.services.data.MainScreenResponse

interface MainScreenRepository {

    suspend fun getContentPhones(): MainScreenResponse

    suspend fun saveHotSalesToDatabase(items:List<HotSales>)

    suspend fun getHotSalesFromDatabase():List<HotSales>

    suspend fun saveBestSellerToDatabase(items:List<BestSeller>)

    suspend fun getBestSellerFromDatabase():List<BestSeller>

}