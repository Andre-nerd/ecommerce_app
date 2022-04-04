package com.zoom_machine.database.detailsscreen_model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DetailsScreenDao {
    @Query("SELECT * FROM ${DetailsContract.TABLE_NAME}")
    suspend fun getDetailsList(): Details

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewDetails(item: Details): Long


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewCapacity(item: Capacity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewColorDevice(item: ColorDevice): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewImage(item: ImagesDevice): Long

    @Query(
        "SELECT * FROM ${ColorDeviceContract.TABLE_NAME}" +
                " WHERE ${ColorDeviceContract.Columns.ID_DETAILS} =:id_details"
    )
    suspend fun getDeviceColor(id_details: String): List<ColorDevice>

    @Query(
        "SELECT * FROM ${ImagesDeviceContract.TABLE_NAME}" +
                " WHERE ${ImagesDeviceContract.Columns.ID_DETAILS} =:id_details"
    )
    suspend fun getDeviceImages(id_details: String): List<ImagesDevice>

    @Query(
        "SELECT * FROM ${CapacityContract.TABLE_NAME}" +
                " WHERE ${CapacityContract.Columns.ID_DETAILS} =:id_details"
    )
    suspend fun getDeviceCapacity(id_details: String): List<Capacity>
}