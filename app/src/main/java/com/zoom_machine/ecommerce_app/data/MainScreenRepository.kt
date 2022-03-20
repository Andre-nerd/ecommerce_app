package com.zoom_machine.ecommerce_app.data

import com.zoom_machine.ecommerce_app.R
import com.zoom_machine.ecommerce_app.presentation.data.TopMenuItem

class MainScreenRepository {

    fun getItemsTopMenu(): List<TopMenuItem> {
        return listOf(
            TopMenuItem(R.drawable.ic_phone, R.string.phones),
            TopMenuItem(R.drawable.ic_computer, R.string.computer),
            TopMenuItem(R.drawable.ic_health, R.string.health),
            TopMenuItem(R.drawable.ic_book, R.string.books),
            TopMenuItem(R.drawable.ic_phone, R.string.phones),
            TopMenuItem(R.drawable.ic_health, R.string.health)
        )
    }
}