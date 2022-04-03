package com.zoom_machine.feature_mainscreen.domain

import com.zoom_machine.api.services.data.HotSales
import javax.inject.Inject

class SaveHotSalesUseCase @Inject constructor(private val repository: MainScreenRepository) {
    suspend fun save(items:List<HotSales>){
        repository.saveHotSalesToDatabase(items)
    }
}