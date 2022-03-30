package com.zoom_machine.feature_detailsscreen.data

import com.zoom_machine.api.services.DetailsScreenService
import com.zoom_machine.api.services.data.ProductDetails
import com.zoom_machine.feature_detailsscreen.domain.DetailScreenRepository
import javax.inject.Inject

class DetailsScreenRepositoryImpl @Inject constructor(
    private val detailsScreenService: DetailsScreenService
) : DetailScreenRepository {
    override suspend fun getDetailsProduct(): ProductDetails {
        return detailsScreenService.getContentForMainScreen()
    }
}