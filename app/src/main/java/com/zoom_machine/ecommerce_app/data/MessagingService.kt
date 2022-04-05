package com.zoom_machine.ecommerce_app.data

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.drawable.ColorDrawable
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.zoom_machine.ecommerce_app.MainActivity
import com.zoom_machine.ecommerce_app.R

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val context = this.applicationContext
        val pendingIntent = getPendingIntent()
        val notificationBuild = NotificationCompat.Builder(
            this,
            NotificationChannels.HIGH_PRIORITY_CHANNEL
        )
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setColor(this.application.getColor(R.color.ocher))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.ic_message)
            .setContentIntent(pendingIntent)

        val notification = notificationBuild.build()
        NotificationManagerCompat.from(context)
            .notify(NOTIFICATION_HIGH_ID, notification)

    }

    private fun getPendingIntent(): PendingIntent {
        val intentNotification = Intent(this, MainActivity::class.java)
        intentNotification.putExtra(FROM_NOTIFICATION, OPEN_CART)
        return PendingIntent.getActivity(
            this.applicationContext,
            REQUEST_CODE,
            intentNotification,
            PendingIntent.FLAG_CANCEL_CURRENT
        )
    }
    companion object {
        const val NOTIFICATION_HIGH_ID = 101
        const val REQUEST_CODE = 3001
        const val OPEN_CART = 205
        const val FROM_NOTIFICATION = "Open from notification"
    }
}
