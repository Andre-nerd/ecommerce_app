package com.zoom_machine.database.detailsscreen_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = ImagesDeviceContract.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Details::class,
        parentColumns = [DetailsContract.Columns.ID],
        childColumns = [ImagesDeviceContract.Columns.ID_DETAILS],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ImagesDevice(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ImagesDeviceContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = ImagesDeviceContract.Columns.ID_DETAILS)
    val id_details: String,
    @ColumnInfo(name = ImagesDeviceContract.Columns.IMAGE)
    val image: String
)
