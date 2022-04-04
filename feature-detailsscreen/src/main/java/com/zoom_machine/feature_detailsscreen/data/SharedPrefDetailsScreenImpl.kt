package com.zoom_machine.feature_detailsscreen.data

import android.content.Context
import javax.inject.Inject

class SharedPrefDetailsScreenImpl @Inject constructor(
    private val context: Context
) : SharedPrefDetailsScreen {
    override fun saveDetailsScreenToSharedPref() {
        val editor = context.getSharedPreferences(
            DATA_IN_DATABASE,
            Context.MODE_PRIVATE
        ).edit()
        editor.run {
            putBoolean(DATA_DETAILS_SCREEN, true)
        }
        editor.apply()
    }

    override fun refreshDetailsScreenToSharedPref() {
        val editor = context.getSharedPreferences(
            DATA_IN_DATABASE,
            Context.MODE_PRIVATE
        ).edit()
        editor.run {
            putBoolean(DATA_DETAILS_SCREEN, false)
        }
        editor.apply()
    }

    override fun readDetailsScreenFromSharedPref(): Boolean {
        return context.getSharedPreferences(
            DATA_IN_DATABASE,
            Context.MODE_PRIVATE
        ).getBoolean(DATA_DETAILS_SCREEN, false)
    }

    companion object {
        const val DATA_IN_DATABASE = "data_in_database"
        const val DATA_DETAILS_SCREEN = "data_details_screen"
    }
}