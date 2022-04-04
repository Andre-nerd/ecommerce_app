package com.zoom_machine.feature_detailsscreen.domain

import com.zoom_machine.api.services.data.ProductDetails
import com.zoom_machine.database.detailsscreen_model.Details

interface DetailScreenRepository {

    suspend fun getDetailsProduct(): ProductDetails

    suspend fun getDetailsFromDatabase(): Details

    suspend fun getDeviceColors(idDevice: String): List<String>

    suspend fun getDeviceCapacity(idDevice: String): List<String>

    suspend fun getDeviceImages(idDevice: String): List<String>

    suspend fun saveDetailsToDatabase(item: Details)

    suspend fun saveDeviceColorsToDataBase(idDevice: String, items: List<String>)

    suspend fun saveDeviceCapacityToDatabase(idDevice: String, items: List<String>)

    suspend fun saveDeviceImagesToDatabase(idDevice: String, items: List<String>)
}