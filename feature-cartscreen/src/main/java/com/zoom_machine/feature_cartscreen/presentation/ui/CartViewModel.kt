package com.zoom_machine.feature_cartscreen.presentation.ui

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
        MutableLiveData(emptyList<com.zoom_machine.api.services.data.Purchases>())
    val purchases: LiveData<List<com.zoom_machine.api.services.data.Purchases>>
        get() = mutablePurchases
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
        viewModelScope.launch(Dispatchers.Main) {
            showProgressBar.value = true
        }
        val job = viewModelScope.launch(Dispatchers.IO) {
            try {
                listOfPurchases = getPurchasesUseCase.getPurchases()
            } catch (t: Throwable) {
                viewModelScope.launch(Dispatchers.Main) {
                    throwableMessage.value = MessageViewModel.CONNECTION_ERROR
                }
            }
        }
        job.join()
        viewModelScope.launch(Dispatchers.Main) {
            mutablePurchases.value = listOfPurchases
            mutableTotal.value = calculateTotal(purchases.value ?: emptyList())
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
            com.zoom_machine.api.services.data.Purchases("Honor 50 6/128GB", 1200f, "", 1),
            com.zoom_machine.api.services.data.Purchases(
                "Asus ROG Phone 5S ZS676KS ",
                1350f,
                "",
                2
            ),
            com.zoom_machine.api.services.data.Purchases("BQ 5560L Trend Black", 1500f, "", 1)
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