package com.zoom_machine.ecommerce_app

import android.app.Application
import com.zoom_machine.database.Database
import com.zoom_machine.ecommerce_app.presentation.di.DaggerAppComponent
import com.zoom_machine.feature_cartscreen.presentation.di.CartScreenDepsStore
import com.zoom_machine.feature_detailsscreen.presentation.di.DetailsScreenDepsStore
import com.zoom_machine.feature_mainscreen.presentation.di.MainScreenDepsStore


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Database.init(this)
        val appComponent = DaggerAppComponent.builder()
            .application(this)
            .provideDatabase(Database.instance.mainScreenDao())
            .build()
        Database.init(this)
        MainScreenDepsStore.deps = appComponent
        DetailsScreenDepsStore.deps = appComponent
        CartScreenDepsStore.deps = appComponent
    }
}
