package com.zoom_machine.feature_cartscreen.domain

import com.zoom_machine.api.services.data.BasketResponse
import com.zoom_machine.api.services.data.Purchases
import com.zoom_machine.database.cart_model.Basket
import com.zoom_machine.database.cart_model.Cart
import javax.inject.Inject

class CartScreenDatabaseUseCase @Inject constructor(private val repository: CartRepository) {

    suspend fun getBasket(): BasketResponse {
        val cart = repository.getCart()
        val basket =
            repository.getBasket(cart.id).map { Purchases(it.id, it.images, it.price, it.title) }
        return BasketResponse(
            basket = basket,
            delivery = cart.delivery,
            id = cart.id,
            total = cart.total
        )
    }

    suspend fun saveBasket(item: BasketResponse) {
        val cart = Cart(
            delivery = item.delivery,
            id = item.id,
            total = item.total
        )
        val basket = item.basket.map { Basket(it.id, item.id, it.images, it.price, it.title) }
        repository.saveCart(cart)
        repository.saveBasket(basket)
    }
}