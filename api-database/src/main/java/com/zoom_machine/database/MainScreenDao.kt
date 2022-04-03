package com.zoom_machine.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zoom_machine.api.services.data.HotSales

@Dao
interface MainScreenDao {
//    @Query("SELECT * FROM ${HotSalesContract.TABLE_NAME}")
//    suspend fun getHotSalesLIst(id_layout: Long): List<HotSales>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewHotSale(item:HotSales): Long
}