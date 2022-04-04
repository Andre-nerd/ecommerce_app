package com.zoom_machine.feature_detailsscreen.domain

import com.zoom_machine.api.services.data.ProductDetails
import com.zoom_machine.database.detailsscreen_model.Details
import javax.inject.Inject

class DetailsScreenDatabaseUseCase @Inject constructor(private val repository: DetailScreenRepository) {

    suspend fun getDetailsProduct(): ProductDetails {
        val details = repository.getDetailsFromDatabase()
        val colors = repository.getDeviceColors(details.id)
        val capacity = repository.getDeviceCapacity(details.id)
        val images = repository.getDeviceImages(details.id)
        return ProductDetails(
            CPU = details.CPU,
            camera = details.camera,
            capacity = capacity,
            color = colors,
            id = details.id,
            images = images,
            isFavorites = details.isFavorites,
            price = details.price,
            rating = details.rating,
            sd = details.sd,
            ssd = details.ssd,
            title = details.title
        )
    }

    suspend fun saveDetailsProduct(product: ProductDetails) {
        val details = Details(
            CPU = product.CPU,
            camera = product.camera,
            id = product.id,
            isFavorites = product.isFavorites,
            price = product.price,
            rating = product.rating,
            sd = product.sd,
            ssd = product.ssd,
            title = product.title
        )
        val colors = product.color
        val capacity = product.capacity
        val images = product.images
        repository.saveDetailsToDatabase(details)
        repository.saveDeviceColorsToDataBase(product.id, colors)
        repository.saveDeviceCapacityToDatabase(product.id, capacity)
        repository.saveDeviceImagesToDatabase(product.id, images)

    }
}