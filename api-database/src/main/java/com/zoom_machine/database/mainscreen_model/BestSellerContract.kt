package com.zoom_machine.database.mainscreen_model

object BestSellerContract {
    const val TABLE_NAME = "best_seller"

    object Columns {
        const val ID = "id"
        const val IS_FAVORITES = "is_favorites"
        const val TITLE = "title"
        const val PRICE_W_DISCOUNT = "price_without_discount"
        const val DISCOUNT_PRICE = "discount_price"
        const val PICTURE = "picture"
    }
}