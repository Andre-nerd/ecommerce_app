package com.zoom_machine.api.services.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import com.zoom_machine.database.mainscreen_model.BestSellerContract


@Entity(tableName = BestSellerContract.TABLE_NAME)
@JsonClass(generateAdapter = true)
data class BestSeller(
    @PrimaryKey
    @ColumnInfo(name = BestSellerContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = BestSellerContract.Columns.IS_FAVORITES)
    val is_favorites: Boolean?,
    @ColumnInfo(name = BestSellerContract.Columns.TITLE)
    val title: String,
    @ColumnInfo(name = BestSellerContract.Columns.PRICE_W_DISCOUNT)
    val price_without_discount: Int,
    @ColumnInfo(name = BestSellerContract.Columns.DISCOUNT_PRICE)
    val discount_price: Int,
    @ColumnInfo(name = BestSellerContract.Columns.PICTURE)
    val picture: String
)