package com.zoom_machine.api.services.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import com.zoom_machine.database.HotSalesContract

@Entity(tableName = HotSalesContract.TABLE_NAME)

@JsonClass(generateAdapter = true)
data class HotSales(
    @PrimaryKey
    @ColumnInfo(name = HotSalesContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = HotSalesContract.Columns.IS_NEW)
    val is_new: Boolean?,
    @ColumnInfo(name = HotSalesContract.Columns.TITLE)
    val title: String,
    @ColumnInfo(name = HotSalesContract.Columns.SUBTITLE)
    val subtitle: String,
    @ColumnInfo(name = HotSalesContract.Columns.PICTURE)
    val picture: String,
    @ColumnInfo(name = HotSalesContract.Columns.IS_BUY)
    val is_buy: Boolean?
)
