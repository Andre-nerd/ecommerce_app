package com.zoom_machine.feature_mainscreen.presentation.ui

import android.util.Log
import androidx.lifecycle.*
import com.zoom_machine.api.services.data.BestSeller
import com.zoom_machine.api.services.data.HotSales
import com.zoom_machine.api.services.data.MainScreenResponse
import com.zoom_machine.core.utils.MessageViewModel
import com.zoom_machine.feature_mainscreen.data.TopMenuItem
import com.zoom_machine.feature_mainscreen.domain.GetPhonesUseCase
import com.zoom_machine.feature_mainscreen.domain.MainScreenDatabaseUseCase
import com.zoom_machine.feature_mainscreen.domain.SharedPrefMainScreenUseCase
import com.zoom_machine.feature_mainscreen.presentation.utils.PHONES
import com.zoom_machine.feature_mainscreen.presentation.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class MainScreenViewModel(
    private val getPhonesUseCase: GetPhonesUseCase,
    private val mainScreenDatabaseUseCase: MainScreenDatabaseUseCase,
    private val sharedPrefMainScreenUseCase: SharedPrefMainScreenUseCase
) : ViewModel() {

    private val mutableItemTopMenu = MutableLiveData<List<TopMenuItem>>(emptyList())
    val itemTopMenu: LiveData<List<TopMenuItem>>
        get() = mutableItemTopMenu
    private val mutableHotSales =
        MutableLiveData<List<HotSales>>(emptyList())
    val hotSales: LiveData<List<HotSales>>
        get() = mutableHotSales
    private val mutableBestSeller =
        MutableLiveData<List<BestSeller>>(emptyList())
    val bestSeller: LiveData<List<BestSeller>>
        get() = mutableBestSeller
    private val mutableStatusFilter = MutableLiveData<Boolean>(false)
    val statusFilter: LiveData<Boolean>
        get() = mutableStatusFilter

    val throwableMessage = SingleLiveEvent<MessageViewModel>()
    val showProgressBar = SingleLiveEvent<Boolean>()
    private var isDataInSharedPref = false

    init {
        mutableStatusFilter.value = false
        isDataInSharedPref = sharedPrefMainScreenUseCase.isFirstLaunch()
    }

    private suspend fun getContentPhones() {
        var listOfHotSales = getEmptyHotSalesList()
        var listOfBestSeller = getEmptyBestSeller()
        viewModelScope.launch(Dispatchers.Main) {
            showProgressBar.value = true
        }
        val job = viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getResponse()
                listOfHotSales = response.hotSales
                listOfBestSeller = response.bestSeller
            } catch (t: Throwable) {
                viewModelScope.launch(Dispatchers.Main) {
                    throwableMessage.value = MessageViewModel.CONNECTION_ERROR
                }
            }
        }
        job.join()
        viewModelScope.launch(Dispatchers.Main) {
            mutableHotSales.value = listOfHotSales
            mutableBestSeller.value = listOfBestSeller
            showProgressBar.value = false
        }
        job.cancel()
    }

    private suspend fun getResponse(): MainScreenResponse {
        var response: MainScreenResponse
        if (isDataInSharedPref) {
            Log.d("2LEVEL", "MainScreen data loading from DB")
            response = MainScreenResponse(
                mainScreenDatabaseUseCase.getHotSalesList(),
                mainScreenDatabaseUseCase.getBestSellerList()
            )

        } else {
            Log.d("2LEVEL", "MainScreen data loading from Server")
            response = getPhonesUseCase.getContentPhones()
            mainScreenDatabaseUseCase.saveHotSales(response.hotSales)
            mainScreenDatabaseUseCase.saveBestSeller(response.bestSeller)
            sharedPrefMainScreenUseCase.mainScreenNoFirstLaunch()
        }
        return response
    }

    fun handlingClickOnTopMenu(itemPosition: Int) {
        setColorToSelectedItem(itemPosition)
        viewModelScope.launch {
            when (itemPosition) {
                PHONES -> getContentPhones()
            }
        }
    }

    private fun setColorToSelectedItem(position: Int) {
        val newList = mutableItemTopMenu.value ?: emptyList()
        if (newList.isNotEmpty()) {
            newList[position].isSelected = true
            newList.forEachIndexed { index, topMenuItem ->
                if (index != position) topMenuItem.isSelected = false
            }
            mutableItemTopMenu.value = newList
        }
    }

    fun changeFilterVisible() {
        val status = !statusFilter.value!!
        mutableStatusFilter.value = status
    }

    fun setItemsTopMenu(list: List<TopMenuItem>) {
        mutableItemTopMenu.value = list
        handlingClickOnTopMenu(PHONES)
    }

    private fun getEmptyHotSalesList(): List<HotSales> {
        return listOf(
            HotSales(1L, true, "No image", " ", " ", false),
            HotSales(2L, true, "No image", " ", " ", false)
        )
    }

    private fun getEmptyBestSeller(): List<BestSeller> {
        return listOf(
            BestSeller(1L, true, "No image", 0, 0, ""),
            BestSeller(2L, false, "No image", 0, 0, ""),
            BestSeller(3L, true, "No image", 0, 0, ""),
            BestSeller(4L, false, "No image", 0, 0, ""),
        )
    }

    class Factory @Inject constructor(
        private val getPhonesUseCase: GetPhonesUseCase,
        private val mainScreenDatabaseUseCase: MainScreenDatabaseUseCase,
        private val sharedPrefMainScreenUseCase: SharedPrefMainScreenUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == MainScreenViewModel::class.java)
            return MainScreenViewModel(
                getPhonesUseCase,
                mainScreenDatabaseUseCase,
                sharedPrefMainScreenUseCase
            ) as T
        }
    }
}