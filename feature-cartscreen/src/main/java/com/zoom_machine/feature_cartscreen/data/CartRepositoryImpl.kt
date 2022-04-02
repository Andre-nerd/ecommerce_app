package com.zoom_machine.feature_cartscreen.data

import com.zoom_machine.api.services.CartScreenService
import com.zoom_machine.api.services.data.BasketResponse
import com.zoom_machine.api.services.data.Purchases
import com.zoom_machine.feature_cartscreen.domain.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartScreenService: CartScreenService
):CartRepository {
    override suspend fun getPurchases(): BasketResponse {
        return cartScreenService.getContentForCartScreen()
    }
}