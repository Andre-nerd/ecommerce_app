package com.zoom_machine.feature_detailsscreen.domain

import com.zoom_machine.api.services.data.ProductDetails
import javax.inject.Inject

class GetDetailsProductUseCase @Inject constructor(
    private val detailScreenRepository: DetailScreenRepository
) {
    suspend fun getDetailsProduct(): ProductDetails {
        return detailScreenRepository.getDetailsProduct()
    }
}