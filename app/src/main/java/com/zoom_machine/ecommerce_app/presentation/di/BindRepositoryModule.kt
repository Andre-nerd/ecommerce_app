package com.zoom_machine.ecommerce_app.presentation.di

import com.zoom_machine.ecommerce_app.data.MainScreenRepositoryImpl
import com.zoom_machine.ecommerce_app.domain.MainScreenRepository
import dagger.Binds
import dagger.Module

@Module
abstract class BindRepositoryModule {

    @Binds
    abstract fun bindAuthRepository(repository: MainScreenRepositoryImpl): MainScreenRepository

}