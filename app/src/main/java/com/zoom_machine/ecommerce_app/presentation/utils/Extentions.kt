package com.zoom_machine.ecommerce_app.presentation.utils

import android.content.Context
import com.zoom_machine.ecommerce_app.App
import com.zoom_machine.ecommerce_app.presentation.di.AppComponent

const val PHONES = 0

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }