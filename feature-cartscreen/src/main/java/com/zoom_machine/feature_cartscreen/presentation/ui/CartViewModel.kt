package com.zoom_machine.feature_cartscreen.presentation.ui

import android.util.Log
import androidx.lifecycle.*
import com.zoom_machine.api.services.data.Purchases
import com.zoom_machine.core.utils.MessageViewModel
import com.zoom_machine.feature_cartscreen.domain.GetPurchasesUseCase
import com.zoom_machine.feature_cartscreen.presentation.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CartViewModel(
    private val getPurchasesUseCase: GetPurchasesUseCase
) : ViewModel() {
    private val mutablePurchases =
        MutableLiveData(emptyList<Purchases>())
    val purchases: LiveData<List<Purchases>>
        get() = mutablePurchases
    private val mutableDeliveryStatus = MutableLiveData<String>()
    val deliveryStatus: LiveData<String>
        get() = mutableDeliveryStatus
    private val mutableTotal = MutableLiveData(0f)
    val total: LiveData<Float>
        get() = mutableTotal
    val throwableMessage = SingleLiveEvent<MessageViewModel>()
    val showProgressBar = SingleLiveEvent<Boolean>()

    init {
        viewModelScope.launch {
            getContentCart()
        }
    }

    private suspend fun getContentCart() {
        var listOfPurchases = dummyListPurchases()
        var deliveryStatus = " "
        viewModelScope.launch(Dispatchers.Main) {
            showProgressBar.value = true
        }
        val job = viewModelScope.launch(Dispatchers.IO) {
            try {
                val response  = getPurchasesUseCase.getPurchases()
                listOfPurchases = response.basket
                deliveryStatus = response.delivery

            } catch (t: Throwable) {
                Log.d("NEWAPI", "Throwable getContentCart() $t")
                viewModelScope.launch(Dispatchers.Main) {
                    throwableMessage.value = MessageViewModel.CONNECTION_ERROR
                }
            }
        }
        job.join()
        viewModelScope.launch(Dispatchers.Main) {
            mutablePurchases.value = listOfPurchases
            mutableTotal.value = calculateTotal(purchases.value ?: emptyList())
            mutableDeliveryStatus.value = deliveryStatus
            showProgressBar.value = false
        }
        job.cancel()
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

    private fun dummyListPurchases(): List<Purchases> {
        return listOf(
            com.zoom_machine.api.services.data.Purchases(1, "", 1200f, "Honor 50 6/128GB", 1),
            com.zoom_machine.api.services.data.Purchases(2, "", 1350f, "Asus ROG Phone 5S", 1),
            com.zoom_machine.api.services.data.Purchases(3, " ", 1500f, "BQ 5560L Trend Black", 1)
        )
    }

    class Factory @Inject constructor(
        private val getPurchasesUseCase: GetPurchasesUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == CartViewModel::class.java)
            return CartViewModel(getPurchasesUseCase) as T
        }
    }
}