package com.zoom_machine.ecommerce_app

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zoom_machine.ecommerce_app.domain.CheckFirstLaunchUseCase
import com.zoom_machine.shared.ConfigApp
import com.zoom_machine.shared.SharedPrefRepository
import com.zoom_machine.ecommerce_app.presentation.di.AppComponent
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    val Context.appComponent: AppComponent
        get() = when (this) {
            is App -> appComponent
            else -> this.applicationContext.appComponent
        }

    @Inject
    lateinit var checkFirstLaunchUseCase: CheckFirstLaunchUseCase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.injectToMainActivity(this)
        ConfigApp.isFirstLaunch = checkFirstLaunchUseCase.checkIsFirstLaunch()
        Log.d("2LEVEL", "isFirstLaunch =${com.zoom_machine.shared.ConfigApp.isFirstLaunch}")
    }
}