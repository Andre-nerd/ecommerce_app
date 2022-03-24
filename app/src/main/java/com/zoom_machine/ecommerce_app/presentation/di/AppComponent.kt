package com.zoom_machine.ecommerce_app.presentation.di

import android.app.Application
import com.zoom_machine.api.services.MainScreenService
import com.zoom_machine.api.services.domain.MainScreenResponse
import com.zoom_machine.api.services.mainScreenService
import com.zoom_machine.feature_mainscreen.domain.GetPhonesUseCase
import com.zoom_machine.feature_mainscreen.domain.MainScreenRepository
import com.zoom_machine.feature_mainscreen.presentation.di.MainScreenDeps
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent : MainScreenDeps {
    override val mainScreenService: MainScreenService

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
}

@Scope
annotation class AppScope


