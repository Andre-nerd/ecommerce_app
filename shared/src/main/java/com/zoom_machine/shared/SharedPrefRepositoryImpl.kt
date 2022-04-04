package com.zoom_machine.shared

import android.content.Context
import javax.inject.Inject

class SharedPrefRepositoryImpl @Inject constructor(
    private val context: Context
) : SharedPrefRepository {
    override fun saveFirstLaunchToSharedPref() {
        val editor = context.getSharedPreferences(
            FIRST_LAUNCH,
            Context.MODE_PRIVATE
        ).edit()
        editor.run {
            putBoolean(IS_FIRST_LAUNCH, false)
        }
        editor.apply()
    }

    override fun readFirstLaunchFromSharedPref(): Boolean {
        return context.getSharedPreferences(
            FIRST_LAUNCH,
            Context.MODE_PRIVATE
        ).getBoolean(IS_FIRST_LAUNCH, true)
    }

    companion object {
        const val FIRST_LAUNCH = "first_launch"
        const val IS_FIRST_LAUNCH = "is_first_launch"
    }
}