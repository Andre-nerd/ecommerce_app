package com.zoom_machine.feature_cartscreen.domain

import com.zoom_machine.api.services.data.Purchases

interface CartRepository {

    suspend fun getPurchases():List<Purchases>
}