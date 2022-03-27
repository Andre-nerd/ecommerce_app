package com.zoom_machine.feature_mainscreen.data


import android.util.Log
import com.zoom_machine.api.services.MainScreenService
import com.zoom_machine.api.services.data.MainScreenResponse
import com.zoom_machine.feature_mainscreen.domain.MainScreenRepository
import javax.inject.Inject

class MainScreenRepositoryImpl @Inject constructor(
    private val mainScreenService: MainScreenService
) : MainScreenRepository {

    override suspend fun getContentPhones(): MainScreenResponse {
        Log.d("NEWAPI","HERE getContentPhones()")
        val list = mainScreenService.getContentForMainScreen()
        Log.d("NEWAPI","List = $list")
        return list
    }

}