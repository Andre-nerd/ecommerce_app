package com.zoom_machine.feature_mainscreen.domain

import com.zoom_machine.api.services.data.BestSeller
import com.zoom_machine.api.services.data.HotSales
import javax.inject.Inject

class MainScreenDatabaseUseCase @Inject constructor(private val repository: MainScreenRepository) {
    suspend fun saveHotSales(items: List<HotSales>) {
        repository.saveHotSalesToDatabase(items)
    }

    suspend fun getHotSalesList(): List<HotSales> {
        return repository.getHotSalesFromDatabase()
    }

    suspend fun saveBestSeller(items: List<BestSeller>) {
        repository.saveBestSellerToDatabase(items)
    }

    suspend fun getBestSellerList(): List<BestSeller> {
        return repository.getBestSellerFromDatabase()
    }
}