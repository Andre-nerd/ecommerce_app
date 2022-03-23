package com.zoom_machine.feature_mainscreen.data


import com.zoom_machine.api.services.MainScreenService
import com.zoom_machine.api.services.domain.MainScreenResponse
import com.zoom_machine.feature_mainscreen.R
import com.zoom_machine.feature_mainscreen.domain.MainScreenRepository
import com.zoom_machine.feature_mainscreen.presentation.ui.ui_components.TopMenuItem
import javax.inject.Inject

class MainScreenRepositoryImpl @Inject constructor() : MainScreenRepository {


    override suspend fun getContentPhones(mainScreenService: MainScreenService): MainScreenResponse {
        return mainScreenService.getContentForMainScreen()[0]
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