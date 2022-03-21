package com.zoom_machine.l_tech_app

import android.app.Application
import com.zoom_machine.l_tech_app.di.AppComponent
import com.zoom_machine.l_tech_app.di.DaggerAppComponent
import com.zoom_machine.l_tech_app.networking.OkHttpClient


class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(context = this)
            .okHttp(OkHttpClient())
            .build()
    }
}
