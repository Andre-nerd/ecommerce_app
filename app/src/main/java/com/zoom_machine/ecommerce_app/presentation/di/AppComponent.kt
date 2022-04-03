package com.zoom_machine.ecommerce_app.presentation.di

import android.app.Application
import com.zoom_machine.api.services.*
import com.zoom_machine.database.MainScreenDao
import com.zoom_machine.feature_cartscreen.presentation.di.CartScreenDeps
import com.zoom_machine.feature_detailsscreen.presentation.di.DetailsScreenDeps
import com.zoom_machine.feature_mainscreen.presentation.di.MainScreenDeps
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent : MainScreenDeps, DetailsScreenDeps, CartScreenDeps {
    override val mainScreenService: MainScreenService
    override val detailsScreenService: DetailsScreenService
    override val cartScreenService: CartScreenService

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        @BindsInstance
        fun provideDatabase(database:MainScreenDao) :Builder
        fun build(): AppComponent
    }
}

@Module
class AppModule {
    @[Provides AppScope]
    fun provideMainScreenService() = mainScreenService()

    @[Provides AppScope]
    fun provideDetailsScreenService() = detailsScreenService()

    @[Provides AppScope]
    fun provideCartScreenService() = cartScreenService()
}

@Scope
annotation class AppScope


