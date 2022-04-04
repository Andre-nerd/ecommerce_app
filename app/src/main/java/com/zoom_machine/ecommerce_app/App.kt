package com.zoom_machine.ecommerce_app

import android.app.Application
import com.zoom_machine.database.Database
import com.zoom_machine.ecommerce_app.presentation.di.AppComponent
import com.zoom_machine.ecommerce_app.presentation.di.DaggerAppComponent
import com.zoom_machine.feature_cartscreen.presentation.di.CartScreenDepsStore
import com.zoom_machine.feature_detailsscreen.presentation.di.DetailsScreenDepsStore
import com.zoom_machine.feature_mainscreen.presentation.di.MainScreenDepsStore


class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Database.init(this)
        val database = Database.instance
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .provideMainScreenDao(database.mainScreenDao())
            .provideDetailsScreenDao(database.detailsScreenDao())
            .provideCartScreenDao(database.cartScreenDao())
            .context(this)
            .build()
        Database.init(this)
        MainScreenDepsStore.deps = appComponent
        DetailsScreenDepsStore.deps = appComponent
        CartScreenDepsStore.deps = appComponent
    }
}
