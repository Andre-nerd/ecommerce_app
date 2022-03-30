package com.zoom_machine.ecommerce_app.presentation.di

import android.app.Application
import com.zoom_machine.api.services.DetailsScreenService
import com.zoom_machine.api.services.MainScreenService
import com.zoom_machine.api.services.detailsScreenService
import com.zoom_machine.api.services.mainScreenService
import com.zoom_machine.feature_detailsscreen.presentation.di.DetailsScreenDeps
import com.zoom_machine.feature_mainscreen.presentation.di.MainScreenDeps
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent : MainScreenDeps,DetailsScreenDeps {
    override val mainScreenService: MainScreenService
    override val detailsScreenService:DetailsScreenService

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}

@Module
class AppModule {
    @[Provides AppScope]
    fun provideMainScreenService() = mainScreenService()
    @[Provides AppScope]
    fun provideDetailsScreenService() = detailsScreenService()
}

@Scope
annotation class AppScope


