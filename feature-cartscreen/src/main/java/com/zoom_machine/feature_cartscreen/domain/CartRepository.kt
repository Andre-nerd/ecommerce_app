package com.zoom_machine.feature_cartscreen.domain

import com.zoom_machine.api.services.data.BasketResponse
import com.zoom_machine.database.cart_model.Basket
import com.zoom_machine.database.cart_model.Cart

interface CartRepository {

    suspend fun getPurchases(): BasketResponse

    suspend fun getCart(): Cart

    suspend fun saveCart(item: Cart)

    suspend fun getBasket(id_cart: String): List<Basket>

    suspend fun saveBasket(items: List<Basket>)
}