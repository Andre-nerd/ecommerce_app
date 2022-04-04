package com.zoom_machine.feature_detailsscreen.data

import com.zoom_machine.api.services.DetailsScreenService
import com.zoom_machine.api.services.data.ProductDetails
import com.zoom_machine.database.detailsscreen_model.*
import com.zoom_machine.feature_detailsscreen.domain.DetailScreenRepository
import javax.inject.Inject

class DetailsScreenRepositoryImpl @Inject constructor(
    private val detailsScreenService: DetailsScreenService,
    private val detailsScreenDao: DetailsScreenDao
) : DetailScreenRepository {
    override suspend fun getDetailsProduct(): ProductDetails {
        return detailsScreenService.getContentForMainScreen()
    }

    override suspend fun getDetailsFromDatabase(): Details {
        return detailsScreenDao.getDetailsList()
    }

    override suspend fun getDeviceColors(idDevice: String): List<String> {
        return detailsScreenDao.getDeviceColor(idDevice).map { it.color }
    }

    override suspend fun getDeviceCapacity(idDevice: String): List<String> {
        return detailsScreenDao.getDeviceCapacity(idDevice).map { it.capacity }
    }

    override suspend fun getDeviceImages(idDevice: String): List<String> {
        return detailsScreenDao.getDeviceImages(idDevice).map { it.image }
    }

    override suspend fun saveDetailsToDatabase(item: Details) {
        detailsScreenDao.insertNewDetails(item)
    }

    override suspend fun saveDeviceColorsToDataBase(idDevice: String, items: List<String>) {
        items.forEach {
            val item = ColorDevice(0, idDevice, it)
            detailsScreenDao.insertNewColorDevice(item)
        }
    }

    override suspend fun saveDeviceCapacityToDatabase(idDevice: String, items: List<String>) {
        items.forEach {
            val item = Capacity(0, idDevice, it)
            detailsScreenDao.insertNewCapacity(item)
        }
    }

    override suspend fun saveDeviceImagesToDatabase(idDevice: String, items: List<String>) {
        items.forEach {
            val item = ImagesDevice(0, idDevice, it)
            detailsScreenDao.insertNewImage(item)
        }
    }
}