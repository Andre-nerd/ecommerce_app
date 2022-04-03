package com.zoom_machine.database

import android.content.Context
import androidx.room.Room

object Database {
    lateinit var instance: DatabaseObject
        private set

    fun init(context: Context) {
        instance = Room.databaseBuilder(
            context,
            DatabaseObject::class.java,
            DatabaseObject.DB_NAME
        )
            .build()
    }
}