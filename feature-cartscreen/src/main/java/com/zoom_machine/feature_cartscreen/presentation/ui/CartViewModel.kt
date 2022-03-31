package com.zoom_machine.feature_cartscreen.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {

    private val mutablePurchases = MutableLiveData(emptyList<com.zoom_machine.api.services.data.Purchases>())
    val purchases: LiveData<List<com.zoom_machine.api.services.data.Purchases>>
        get() = mutablePurchases
    private val mutableTotal = MutableLiveData(0f)
    val total: LiveData<Float>
        get() = mutableTotal

    init {
        mokieListPurchases()
        mutableTotal.value = calculateTotal(purchases.value ?: emptyList())

    }

    fun mokieListPurchases() {
        val list = listOf(
            com.zoom_machine.api.services.data.Purchases("NO name", 1200f, "", 1),
            com.zoom_machine.api.services.data.Purchases("NO name", 1350f, "", 2),
            com.zoom_machine.api.services.data.Purchases("NO name", 1500f, "", 1)
        )
        mutablePurchases.value = list
    }

    fun setCountToItem(item: Int, count: Int) {
        val list = purchases.value ?: emptyList()
        if (item <= list.size) {
            list[item].count = count
        }
        mutablePurchases.value = list
        mutableTotal.value = calculateTotal(purchases.value ?: emptyList())
    }

    private fun calculateTotal(list: List<com.zoom_machine.api.services.data.Purchases>): Float {
        var total: Float = 0f
        list.forEach {
            total += it.count * it.price
        }
        return total
    }
}