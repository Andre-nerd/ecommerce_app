package com.zoom_machine.feature_cartscreen.domain

import com.zoom_machine.api.services.data.BasketResponse
import com.zoom_machine.api.services.data.Purchases
import javax.inject.Inject

class GetPurchasesUseCase @Inject constructor(
    private val repository:CartRepository
):CartRepository {
    override suspend fun getPurchases(): BasketResponse {
        return repository.getPurchases()
    }
}