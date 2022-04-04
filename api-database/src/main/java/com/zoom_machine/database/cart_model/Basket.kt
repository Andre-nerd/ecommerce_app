package com.zoom_machine.database.cart_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = BasketContract.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Cart::class,
        parentColumns = [CartContract.Columns.ID],
        childColumns = [BasketContract.Columns.ID_CART],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Basket(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = BasketContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = BasketContract.Columns.ID_CART)
    val id_cart: String,
    @ColumnInfo(name = BasketContract.Columns.IMAGES)
    val images: String,
    @ColumnInfo(name = BasketContract.Columns.PRICE)
    val price: Float,
    @ColumnInfo(name = BasketContract.Columns.TITLE)
    val title: String
)
