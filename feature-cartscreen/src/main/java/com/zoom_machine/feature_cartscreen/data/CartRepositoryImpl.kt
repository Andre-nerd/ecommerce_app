package com.zoom_machine.feature_cartscreen.data

import com.zoom_machine.api.services.data.Purchases
import com.zoom_machine.feature_cartscreen.domain.CartRepository

class CartRepositoryImpl:CartRepository {
    override suspend fun getPurchases(): List<Purchases> {
        TODO("Not yet implemented")
    }
}