package com.zoom_machine.ecommerce_app.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zoom_machine.ecommerce_app.data.MainScreenRepository
import com.zoom_machine.ecommerce_app.presentation.data.TopMenuItem

class MainScreenViewModel : ViewModel() {
    private val repository = MainScreenRepository()

    private val mutableItemTopMenu = MutableLiveData<List<TopMenuItem>>(emptyList())
    val itemTopMenu: LiveData<List<TopMenuItem>> = mutableItemTopMenu

    init {
        val list = getItemsTopMenu()
        mutableItemTopMenu.value = list
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