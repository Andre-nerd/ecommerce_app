package com.zoom_machine.database.detailsscreen_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = ColorDeviceContract.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Details::class,
        parentColumns = [DetailsContract.Columns.ID],
        childColumns = [ColorDeviceContract.Columns.ID_DETAILS],
        onDelete = ForeignKey.CASCADE
    )]
)

data class ColorDevice(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ColorDeviceContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = ColorDeviceContract.Columns.ID_DETAILS)
    val id_details: String,
    @ColumnInfo(name = ColorDeviceContract.Columns.COLOR)
    val color: String
)

