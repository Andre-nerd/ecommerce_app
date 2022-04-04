package com.zoom_machine.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zoom_machine.api.services.data.BestSeller
import com.zoom_machine.api.services.data.HotSales
import com.zoom_machine.database.DatabaseObject.Companion.DB_VERSION
import com.zoom_machine.database.cart_model.Basket
import com.zoom_machine.database.cart_model.Cart
import com.zoom_machine.database.cart_model.CartScreenDao
import com.zoom_machine.database.detailsscreen_model.*
import com.zoom_machine.database.mainscreen_model.MainScreenDao

@Database(
    entities = [
        HotSales::class,
        BestSeller::class,
        Details::class,
        Capacity::class,
        ColorDevice::class,
        ImagesDevice::class,
        Basket::class,
        Cart::class
    ],
    version = DB_VERSION
)
abstract class DatabaseObject : RoomDatabase() {
    abstract fun mainScreenDao(): MainScreenDao
    abstract fun detailsScreenDao(): DetailsScreenDao
    abstract fun cartScreenDao(): CartScreenDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "ecommerce app"
    }
}
