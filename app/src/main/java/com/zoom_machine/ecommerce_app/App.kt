package com.zoom_machine.ecommerce_app

import android.app.Application
import com.zoom_machine.ecommerce_app.presentation.di.AppComponent
import com.zoom_machine.ecommerce_app.presentation.di.DaggerAppComponent


class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .build()
    }
}
