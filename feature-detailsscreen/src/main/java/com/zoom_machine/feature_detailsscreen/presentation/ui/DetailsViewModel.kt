package com.zoom_machine.feature_detailsscreen.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoom_machine.api.services.data.ProductDetails
import com.zoom_machine.core.utils.MessageViewModel
import com.zoom_machine.feature_detailsscreen.data.DetailsScreenRepositoryImpl
import com.zoom_machine.feature_detailsscreen.data.ProductSpecification
import com.zoom_machine.feature_detailsscreen.presentation.utils.NO_INFO
import com.zoom_machine.feature_detailsscreen.presentation.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val repository = DetailsScreenRepositoryImpl()

    private val mutableProduct = MutableLiveData<ProductDetails>()
    val product: LiveData<ProductDetails> = mutableProduct
    private val mutableSpecification = MutableLiveData<ProductSpecification>()
    val specification: LiveData<ProductSpecification> = mutableSpecification
    val throwableMessage = SingleLiveEvent<MessageViewModel>()
    val showProgressBar = SingleLiveEvent<Boolean>()

    suspend fun getDetailsProduct() {
        var response = getEmptyProductDetails()
        viewModelScope.launch(Dispatchers.Main) {
            showProgressBar.value = true
        }
        val job = viewModelScope.launch(Dispatchers.IO) {
            try {
                response = repository.getDetailsProduct()
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
            NO_INFO, NO_INFO, listOf(NO_INFO), listOf(NO_INFO), NO_INFO,
            listOf(NO_INFO), false, 0, 1F, NO_INFO, NO_INFO, NO_INFO
        )
    }

    fun ProductDetails.mapSpecification(): ProductSpecification {
        return ProductSpecification(this.CPU, this.camera, this.ssd, this.sd)
    }

}