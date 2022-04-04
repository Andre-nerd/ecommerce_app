package com.zoom_machine.feature_cartscreen.data

import com.zoom_machine.api.services.CartScreenService
import com.zoom_machine.api.services.data.BasketResponse
import com.zoom_machine.database.cart_model.Basket
import com.zoom_machine.database.cart_model.Cart
import com.zoom_machine.database.cart_model.CartScreenDao
import com.zoom_machine.feature_cartscreen.domain.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartScreenService: CartScreenService,
    private val cartScreenDao: CartScreenDao
) : CartRepository {
    override suspend fun getPurchases(): BasketResponse {
        return cartScreenService.getContentForCartScreen()
    }

    override suspend fun getCart(): Cart {
        return cartScreenDao.getCart()
    }

    override suspend fun saveCart(item: Cart) {
        cartScreenDao.insertNewCart(item)
    }

    override suspend fun getBasket(id_cart: String): List<Basket> {
        return cartScreenDao.getBasketList(id_cart)
    }

    override suspend fun saveBasket(items: List<Basket>) {
        items.forEach {
            cartScreenDao.insertNewBasket(it)
        }
    }
}