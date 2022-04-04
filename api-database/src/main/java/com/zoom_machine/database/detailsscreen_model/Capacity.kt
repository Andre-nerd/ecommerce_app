package com.zoom_machine.database.detailsscreen_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = CapacityContract.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Details::class,
        parentColumns = [DetailsContract.Columns.ID],
        childColumns = [CapacityContract.Columns.ID_DETAILS],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Capacity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = CapacityContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = CapacityContract.Columns.ID_DETAILS)
    val id_details: String,
    @ColumnInfo(name = CapacityContract.Columns.CAPACITY)
    val capacity: String
)
