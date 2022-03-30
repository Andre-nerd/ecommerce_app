package com.zoom_machine.feature_detailsscreen.domain

import com.zoom_machine.api.services.data.ProductDetails

interface DetailScreenRepository {

    suspend fun getDetailsProduct(): ProductDetails
}