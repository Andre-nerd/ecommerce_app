package com.zoom_machine.l_tech_app.di

import dagger.assisted.AssistedFactory

@AssistedFactory
interface ViewModelAssistedFactory {
    fun create(): ViewModelFactory
}