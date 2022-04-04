package com.zoom_machine.database.detailsscreen_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DetailsContract.TABLE_NAME)
data class Details(
    @ColumnInfo(name = DetailsContract.Columns.CPU)
    val CPU: String,
    @ColumnInfo(name = DetailsContract.Columns.CAMERA)
    val camera: String,
    @PrimaryKey
    @ColumnInfo(name = DetailsContract.Columns.ID)
    val id: String,
    @ColumnInfo(name = DetailsContract.Columns.IS_FAVORITES)
    var isFavorites: Boolean?,
    @ColumnInfo(name = DetailsContract.Columns.PRICE)
    val price: Float,
    @ColumnInfo(name = DetailsContract.Columns.RATING)
    val rating: Float,
    @ColumnInfo(name = DetailsContract.Columns.SD)
    val sd: String,
    @ColumnInfo(name = DetailsContract.Columns.SSD)
    val ssd: String,
    @ColumnInfo(name = DetailsContract.Columns.TITLE)
    val title: String
)