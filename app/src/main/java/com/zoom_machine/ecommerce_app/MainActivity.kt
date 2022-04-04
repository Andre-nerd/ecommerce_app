package com.zoom_machine.ecommerce_app

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zoom_machine.ecommerce_app.presentation.di.AppComponent
import com.zoom_machine.feature_mainscreen.data.SharedPrefMainScreen
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    val Context.appComponent: AppComponent
        get() = when (this) {
            is App -> appComponent
            else -> this.applicationContext.appComponent
        }
    @Inject
    lateinit var sharedPrefMainScreen: SharedPrefMainScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.injectToMainActivity(this)
    }

    override fun onPause() {
        sharedPrefMainScreen.refreshDataMainScreenToSharedPref()
        super.onPause()
    }
}