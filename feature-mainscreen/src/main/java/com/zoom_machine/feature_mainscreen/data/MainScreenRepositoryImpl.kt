package com.zoom_machine.feature_mainscreen.data


import com.zoom_machine.api.services.MainScreenService
import com.zoom_machine.api.services.data.BestSeller
import com.zoom_machine.api.services.data.HotSales
import com.zoom_machine.api.services.data.MainScreenResponse
import com.zoom_machine.database.mainscreen_model.MainScreenDao
import com.zoom_machine.feature_mainscreen.domain.MainScreenRepository
import javax.inject.Inject

class MainScreenRepositoryImpl @Inject constructor(
    private val mainScreenService: MainScreenService,
    private val mainScreenDao: MainScreenDao
) : MainScreenRepository {

    override suspend fun getContentPhones(): MainScreenResponse {
        return mainScreenService.getContentForMainScreen()
    }

    override suspend fun saveHotSalesToDatabase(items: List<HotSales>) {
        items.forEach {
            mainScreenDao.insertNewHotSale(it)
        }
    }

    override suspend fun getHotSalesFromDatabase(): List<HotSales> {
       return mainScreenDao.getHotSalesList()
    }

    override suspend fun saveBestSellerToDatabase(items: List<BestSeller>) {
        items.forEach {
            mainScreenDao.insertNewBestSeller(it)
        }
    }

    override suspend fun getBestSellerFromDatabase(): List<BestSeller> {
        return mainScreenDao.getBestSellerList()
    }
}