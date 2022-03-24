package com.zoom_machine.feature_mainscreen.presentation.ui

import androidx.lifecycle.*
import com.zoom_machine.api.services.data.BestSeller
import com.zoom_machine.api.services.data.HotSales
import com.zoom_machine.feature_mainscreen.domain.GetPhonesUseCase
import com.zoom_machine.feature_mainscreen.presentation.ui.ui_components.TopMenuItem
import com.zoom_machine.feature_mainscreen.presentation.utils.MessageViewModel
import com.zoom_machine.feature_mainscreen.presentation.utils.PHONES
import com.zoom_machine.feature_mainscreen.presentation.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class MainScreenViewModel(
    private val getPhonesUseCase: GetPhonesUseCase
) : ViewModel() {

    private val mutableItemTopMenu = MutableLiveData<List<TopMenuItem>>(emptyList())
    val itemTopMenu: LiveData<List<TopMenuItem>> = mutableItemTopMenu

    private val mutableHotSales =
        MutableLiveData<List<HotSales>>(emptyList())
    val hotSales: LiveData<List<HotSales>> = mutableHotSales

    private val mutableBestSeller =
        MutableLiveData<List<BestSeller>>(emptyList())
    val bestSeller: LiveData<List<BestSeller>> = mutableBestSeller

    private val mutableStatusFilter = MutableLiveData<Boolean>(false)
    val statusFilter: LiveData<Boolean> = mutableStatusFilter

    val throwableMessage = SingleLiveEvent<MessageViewModel>()
    val showProgressBar = SingleLiveEvent<Boolean>()

    private suspend fun getContentPhones() {
        var listOfHotSales: List<HotSales> = emptyList()
        var listOfBestSeller: List<BestSeller> = emptyList()
        viewModelScope.launch(Dispatchers.Main) {
            showProgressBar.value = true
        }
        val job = viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getPhonesUseCase.getContentPhones()
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

    class Factory @Inject constructor(
        private val getPhonesUseCase: GetPhonesUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == MainScreenViewModel::class.java)
            return MainScreenViewModel(getPhonesUseCase) as T
        }
    }

    fun setItemsTopMenu(list: List<TopMenuItem>) {
        mutableItemTopMenu.value = list
        handlingClickOnTopMenu(PHONES)
    }
}