package com.zoom_machine.ecommerce_app

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zoom_machine.ecommerce_app.data.MessagingService.Companion.FROM_NOTIFICATION
import com.zoom_machine.ecommerce_app.data.MessagingService.Companion.OPEN_CART
import com.zoom_machine.ecommerce_app.presentation.di.AppComponent
import com.zoom_machine.feature_cartscreen.data.SharedPrefCartScreen
import com.zoom_machine.feature_detailsscreen.data.SharedPrefDetailsScreen
import com.zoom_machine.feature_mainscreen.data.SharedPrefMainScreen
import com.zoom_machine.feature_mainscreen.presentation.utils.LaunchMode
import com.zoom_machine.feature_mainscreen.presentation.utils.LaunchMode.NOTIFICATION
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    val Context.appComponent: AppComponent
        get() = when (this) {
            is App -> appComponent
            else -> this.applicationContext.appComponent
        }

    @Inject
    lateinit var sharedPrefMainScreen: SharedPrefMainScreen

    @Inject
    lateinit var sharedPrefDetailsScreen: SharedPrefDetailsScreen

    @Inject
    lateinit var sharedPrefCartScreen: SharedPrefCartScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("2LEVEL","Activity = $this")
        setContentView(R.layout.activity_main)
        appComponent.injectToMainActivity(this)
        val launchMode = intent.getIntExtra(FROM_NOTIFICATION, 0)
        val deepLink = intent.data?.lastPathSegment ?: ""
        if (launchMode == OPEN_CART || deepLink == LAUNCH_CART) {
            LaunchMode.value = NOTIFICATION
            clearAllNotifications()
        }
    }

    private fun clearAllNotifications() {
        val notificationManager =
            this.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()
    }

    override fun onPause() {
        sharedPrefMainScreen.refreshDataMainScreenToSharedPref()
        sharedPrefDetailsScreen.refreshDetailsScreenToSharedPref()
        sharedPrefCartScreen.refreshCartScreenToSharedPref()
        super.onPause()
    }
    companion object{
        const val LAUNCH_CART = "cart"
    }
}