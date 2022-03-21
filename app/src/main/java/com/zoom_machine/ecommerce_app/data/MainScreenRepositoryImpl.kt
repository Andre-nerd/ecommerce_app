package com.zoom_machine.ecommerce_app.data

import com.zoom_machine.ecommerce_app.R
import com.zoom_machine.ecommerce_app.data.networking.RetrofitApi
import com.zoom_machine.ecommerce_app.domain.MainScreenRepository
import com.zoom_machine.ecommerce_app.presentation.ui.ui_components.TopMenuItem
import javax.inject.Inject

class MainScreenRepositoryImpl @Inject constructor(
    private val api: RetrofitApi
) : MainScreenRepository {

    override suspend fun getContentPhones(): MainScreenResponse {
        return api.apiMainScreen.getContentForMainScreen()[0]
    }

    override fun getItemsTopMenu(): List<TopMenuItem> {
        return listOf(
            TopMenuItem(R.drawable.ic_phone, R.string.phones, true),
            TopMenuItem(R.drawable.ic_computer, R.string.computer),
            TopMenuItem(R.drawable.ic_health, R.string.health),
            TopMenuItem(R.drawable.ic_book, R.string.books),
            TopMenuItem(R.drawable.ic_phone, R.string.phones),
            TopMenuItem(R.drawable.ic_health, R.string.health)
        )
    }
}