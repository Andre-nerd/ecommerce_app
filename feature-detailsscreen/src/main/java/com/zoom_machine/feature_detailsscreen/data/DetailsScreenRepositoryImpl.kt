package com.zoom_machine.feature_detailsscreen.data

import com.zoom_machine.api.services.data.ProductDetails
import com.zoom_machine.api.services.detailsScreenService
import com.zoom_machine.feature_detailsscreen.domain.DetailScreenRepository

class DetailsScreenRepositoryImpl : DetailScreenRepository {
    override suspend fun getDetailsProduct(): ProductDetails {
        return detailsScreenService().getContentForMainScreen()
    }
}