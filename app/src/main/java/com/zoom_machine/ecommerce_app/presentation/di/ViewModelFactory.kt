package com.zoom_machine.ecommerce_app.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zoom_machine.ecommerce_app.domain.MainScreenRepository
import com.zoom_machine.ecommerce_app.presentation.ui.MainScreenViewModel
import dagger.assisted.AssistedInject


class ViewModelFactory @AssistedInject constructor(
    private val mainScreenRepository: MainScreenRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainScreenViewModel::class.java -> MainScreenViewModel(mainScreenRepository) as T
            else -> null as T
        }
    }
}