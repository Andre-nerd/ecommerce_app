package com.zoom_machine.database.mainscreen_model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zoom_machine.api.services.data.HotSales

@Dao
interface MainScreenDao {
    @Query("SELECT * FROM ${HotSalesContract.TABLE_NAME}")
    suspend fun getHotSalesList(): List<HotSales>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewHotSale(item:HotSales): Long
}