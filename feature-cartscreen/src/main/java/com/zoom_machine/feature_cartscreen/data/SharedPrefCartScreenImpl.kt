package com.zoom_machine.feature_cartscreen.data

import android.content.Context
import javax.inject.Inject

class SharedPrefCartScreenImpl @Inject constructor(
    private val context: Context
) : SharedPrefCartScreen {
    override fun saveCartScreenToSharedPref() {
        val editor = context.getSharedPreferences(
            DATA_IN_DATABASE,
            Context.MODE_PRIVATE
        ).edit()
        editor.run {
            putBoolean(DATA_CART_SCREEN, true)
        }
        editor.apply()
    }

    override fun refreshCartScreenToSharedPref() {
        val editor = context.getSharedPreferences(
            DATA_IN_DATABASE,
            Context.MODE_PRIVATE
        ).edit()
        editor.run {
            putBoolean(DATA_CART_SCREEN, false)
        }
        editor.apply()
    }

    override fun readCartScreenFromSharedPref(): Boolean {
        return context.getSharedPreferences(
            DATA_IN_DATABASE,
            Context.MODE_PRIVATE
        ).getBoolean(DATA_CART_SCREEN, false)
    }

    companion object {
        const val DATA_IN_DATABASE = "data_in_database"
        const val DATA_CART_SCREEN = "data_cart_screen"
    }
}