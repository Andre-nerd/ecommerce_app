package com.zoom_machine.ecommerce_app

import android.app.Application
import com.zoom_machine.api.services.domain.MainScreenResponse
import com.zoom_machine.ecommerce_app.presentation.di.DaggerAppComponent
import com.zoom_machine.feature_mainscreen.presentation.di.MainScreenDepsStore


class App : Application() {
    val appComponent = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        MainScreenDepsStore.deps = appComponent
    }
}
