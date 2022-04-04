package com.zoom_machine.database.cart_model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartScreenDao {
    @Query("SELECT * FROM ${CartContract.TABLE_NAME}")
    suspend fun getCart(): Cart

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewCart(item: Cart): Long


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewBasket(item: Basket): Long

    @Query(
        "SELECT * FROM ${BasketContract.TABLE_NAME}" +
                " WHERE ${BasketContract.Columns.ID_CART} =:id_cart"
    )
    suspend fun getBasketList(id_cart: String): List<Basket>
}