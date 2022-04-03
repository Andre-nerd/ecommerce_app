package com.zoom_machine.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zoom_machine.api.services.data.HotSales
import com.zoom_machine.database.DatabaseObject.Companion.DB_VERSION

@Database(
    entities = [HotSales::class],
    version = DB_VERSION
)
abstract class DatabaseObject : RoomDatabase() {
    abstract fun mainScreenDao(): MainScreenDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "industrial_objects_database"
    }
}
