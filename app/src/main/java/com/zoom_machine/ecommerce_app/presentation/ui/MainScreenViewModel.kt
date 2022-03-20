package com.zoom_machine.ecommerce_app.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoom_machine.ecommerce_app.data.BestSeller
import com.zoom_machine.ecommerce_app.data.HotSales
import com.zoom_machine.ecommerce_app.data.MainScreenRepository
import com.zoom_machine.ecommerce_app.presentation.ui.ui_components.TopMenuItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {
    private val repository = MainScreenRepository()

    private val mutableItemTopMenu = MutableLiveData<List<TopMenuItem>>(emptyList())
    val itemTopMenu: LiveData<List<TopMenuItem>> = mutableItemTopMenu

    private val mutableHotSales = MutableLiveData<List<HotSales>>(emptyList())
    val hotSales: LiveData<List<HotSales>> = mutableHotSales

    private val mutableBestSeller = MutableLiveData<List<BestSeller>>(emptyList())
    val bestSeller: LiveData<List<BestSeller>> = mutableBestSeller

    init {
        val list = getItemsTopMenu()
        mutableItemTopMenu.value = list
    }

    suspend fun getContent() {
        var listOfHotSales: List<HotSales> = emptyList()
        var listOfBestSeller: List<BestSeller> = emptyList()
        val job = viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getContent()
            listOfHotSales = response.hotSales
            listOfBestSeller = response.bestSeller
        }
        job.join()
        viewModelScope.launch(Dispatchers.Main) {
            mutableHotSales.value = listOfHotSales
            mutableBestSeller.value = listOfBestSeller
        }
        job.cancel()

    }

    fun handlingClickOnTopMenu(position: Int) {
        val newList = mutableItemTopMenu.value ?: emptyList()
        newList[position].isSelected = true
        newList.forEachIndexed { index, topMenuItem ->
            if (index != position) topMenuItem.isSelected = false
        }
        mutableItemTopMenu.value = newList
    }

    private fun getItemsTopMenu(): List<TopMenuItem> {
        return repository.getItemsTopMenu()
    }
}