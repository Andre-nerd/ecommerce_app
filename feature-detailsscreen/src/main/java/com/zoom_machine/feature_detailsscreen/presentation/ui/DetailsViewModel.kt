package com.zoom_machine.feature_detailsscreen.presentation.ui

import androidx.lifecycle.*
import com.zoom_machine.api.services.data.ProductDetails
import com.zoom_machine.core.utils.MessageViewModel
import com.zoom_machine.feature_detailsscreen.data.ProductSpecification
import com.zoom_machine.feature_detailsscreen.domain.GetDetailsProductUseCase
import com.zoom_machine.feature_detailsscreen.presentation.utils.NO_INFO
import com.zoom_machine.feature_detailsscreen.presentation.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel(
    private val detailsProductUseCase: GetDetailsProductUseCase
) : ViewModel() {
    private val mutableProduct = MutableLiveData<ProductDetails>()
    val product: LiveData<ProductDetails>
        get() = mutableProduct
    private val mutableSpecification = MutableLiveData<List<ProductSpecification>>()
    val specification: LiveData<List<ProductSpecification>>
        get() = mutableSpecification
    private val mutableCapacity = MutableLiveData<Int>(0)
    val capacity: LiveData<Int>
        get() = mutableCapacity
    private val mutableColorDevice = MutableLiveData(0)
    val colorDevice: LiveData<Int>
        get() = mutableColorDevice
    private val mutableCountPurchases = MutableLiveData(0)
    val countPurchases: LiveData<Int>
        get() = mutableCountPurchases
    val throwableMessage = SingleLiveEvent<MessageViewModel>()
    val showProgressBar = SingleLiveEvent<Boolean>()

    suspend fun getDetailsProduct() {
        var response = getEmptyProductDetails()
        viewModelScope.launch(Dispatchers.Main) {
            showProgressBar.value = true
        }
        val job = viewModelScope.launch(Dispatchers.IO) {
            try {
                response = detailsProductUseCase.getDetailsProduct()
            } catch (t: Throwable) {
                viewModelScope.launch(Dispatchers.Main) {
                    throwableMessage.value = MessageViewModel.CONNECTION_ERROR
                }
            }
        }
        job.join()
        viewModelScope.launch(Dispatchers.Main) {
            mutableProduct.value = response
            mutableSpecification.value = response.mapSpecification()
            showProgressBar.value = false
        }
        job.cancel()
    }

    private fun getEmptyProductDetails(): ProductDetails {
        return ProductDetails(
            NO_INFO, NO_INFO, listOf(NO_INFO, NO_INFO), listOf("#772D03", "#010035"), NO_INFO,
            listOf(NO_INFO, NO_INFO), false, 1200F, 3.5F, NO_INFO, NO_INFO, NO_INFO
        )
    }

    private fun ProductDetails.mapSpecification(): List<ProductSpecification> {
        return List(3) { ProductSpecification(this.CPU, this.camera, this.ssd, this.sd) }
    }

    fun setFavorite() {
        val newProduct = product.value
        if (newProduct != null) {
            newProduct.isFavorites = !(product.value?.isFavorites ?: false)
        }
        mutableProduct.value = newProduct ?: getEmptyProductDetails()
    }

    fun setCapacity(value: Int) {
        mutableCapacity.postValue(value)
    }

    fun setColorDevice(value: Int) {
        mutableColorDevice.postValue(value)
    }

    fun addPurchasesCount() {
        mutableCountPurchases.value = mutableCountPurchases.value?.plus(1)
    }

    class Factory @Inject constructor(
        private val detailsProductUseCase: GetDetailsProductUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == DetailsViewModel::class.java)
            return DetailsViewModel(detailsProductUseCase) as T
        }
    }
}