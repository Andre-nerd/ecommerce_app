package com.zoom_machine.feature_mainscreen.data

import android.content.Context
import javax.inject.Inject

class SharedPrefMainScreenImpl @Inject constructor(
    private val context: Context
) : SharedPrefMainScreen {
    override fun saveDataMainScreenToSharedPref() {
        val editor = context.getSharedPreferences(
            DATA_IN_DATABASE,
            Context.MODE_PRIVATE
        ).edit()
        editor.run {
            putBoolean(DATA_MAIN_SCREEN, true)
        }
        editor.apply()
    }

    override fun refreshDataMainScreenToSharedPref() {
        val editor = context.getSharedPreferences(
            DATA_IN_DATABASE,
            Context.MODE_PRIVATE
        ).edit()
        editor.run {
            putBoolean(DATA_MAIN_SCREEN, false)
        }
        editor.apply()
    }

    override fun readDataMainScreenFromSharedPref(): Boolean {
        return context.getSharedPreferences(
            DATA_IN_DATABASE,
            Context.MODE_PRIVATE
        ).getBoolean(DATA_MAIN_SCREEN, false)
    }

    companion object {
        const val DATA_IN_DATABASE = "data_in_database"
        const val DATA_MAIN_SCREEN = "data_main_screen"
    }
}