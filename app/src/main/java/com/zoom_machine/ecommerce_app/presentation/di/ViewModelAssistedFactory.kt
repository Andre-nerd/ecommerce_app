package com.zoom_machine.ecommerce_app.presentation.di

import dagger.assisted.AssistedFactory

@AssistedFactory
interface ViewModelAssistedFactory {
    fun create(): ViewModelFactory
}