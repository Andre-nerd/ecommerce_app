package com.zoom_machine.ecommerce_app.presentation.di

import android.app.Application
import android.content.Context
import com.zoom_machine.api.services.*
import com.zoom_machine.database.cart_model.CartScreenDao
import com.zoom_machine.database.detailsscreen_model.DetailsScreenDao
import com.zoom_machine.database.mainscreen_model.MainScreenDao
import com.zoom_machine.ecommerce_app.MainActivity
import com.zoom_machine.feature_cartscreen.data.SharedPrefCartScreen
import com.zoom_machine.feature_cartscreen.data.SharedPrefCartScreenImpl
import com.zoom_machine.feature_cartscreen.presentation.di.CartScreenDeps
import com.zoom_machine.feature_detailsscreen.data.SharedPrefDetailsScreen
import com.zoom_machine.feature_detailsscreen.data.SharedPrefDetailsScreenImpl
import com.zoom_machine.feature_detailsscreen.presentation.di.DetailsScreenDeps
import com.zoom_machine.feature_mainscreen.data.SharedPrefMainScreen
import com.zoom_machine.feature_mainscreen.data.SharedPrefMainScreenImpl
import com.zoom_machine.feature_mainscreen.presentation.di.MainScreenDeps
import dagger.*
import javax.inject.Scope

@[AppScope Component(modules = [AppModule::class, BindSharedPrefRepository::class])]
interface AppComponent : MainScreenDeps, DetailsScreenDeps, CartScreenDeps {
    override val mainScreenService: MainScreenService
    override val detailsScreenService: DetailsScreenService
    override val cartScreenService: CartScreenService

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun provideMainScreenDao(mainScreenDao: MainScreenDao): Builder

        @BindsInstance
        fun provideDetailsScreenDao(detailsScreenDao: DetailsScreenDao): Builder

        @BindsInstance
        fun provideCartScreenDao(cartScreenDao: CartScreenDao): Builder
        fun build(): AppComponent
    }

    fun injectToMainActivity(activity: MainActivity)
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

@Module
interface BindSharedPrefRepository {
    @Binds
    fun bindSharedPrefMainScreenRepository(repository: SharedPrefMainScreenImpl): SharedPrefMainScreen

    @Binds
    fun bindSharedPrefDetailsScreenRepository(repository: SharedPrefDetailsScreenImpl): SharedPrefDetailsScreen

    @Binds
    fun bindSharedPrefCartScreenRepository(repository: SharedPrefCartScreenImpl): SharedPrefCartScreen
}

@Scope
annotation class AppScope


