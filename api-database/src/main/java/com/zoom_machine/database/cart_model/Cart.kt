package com.zoom_machine.database.cart_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CartContract.TABLE_NAME)
data class Cart(
    @ColumnInfo(name = CartContract.Columns.DELIVERY)
    val delivery: String,
    @PrimaryKey
    @ColumnInfo(name = CartContract.Columns.ID)
    val id: String,
    @ColumnInfo(name = CartContract.Columns.TOTAL)
    val total: Float
)