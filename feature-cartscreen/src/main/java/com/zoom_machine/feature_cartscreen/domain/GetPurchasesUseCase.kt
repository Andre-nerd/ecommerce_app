package com.zoom_machine.feature_cartscreen.domain

import com.zoom_machine.api.services.data.BasketResponse
import javax.inject.Inject

class GetPurchasesUseCase @Inject constructor(private val repository: CartRepository) {
    suspend fun getPurchases(): BasketResponse {
        return repository.getPurchases()
    }
}