package com.zoom_machine.ecommerce_app.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat

object NotificationChannels {
    const val HIGH_PRIORITY_CHANNEL = "2001"
    const val LOW_PRIORITY_CHANNEL = "1001"
    const val URGENT = "Urgent notice"
    const val IMPORTANT_NOTICES = "Important Notices"
    const val NOTICES = "Notices"
    const val NOTICE = "Notice"

    fun create(context: Context) {
        createHighPriorityChannel(context)
        createLowPriorityChannel(context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createHighPriorityChannel(context: Context) {
        val name = URGENT
        val channelDescription = IMPORTANT_NOTICES
        val priority = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(
            HIGH_PRIORITY_CHANNEL,
            name,
            priority
        ).apply {
            description = channelDescription
            enableVibration(true)
            vibrationPattern = longArrayOf(100, 100, 500, 500)
            setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), null)
        }
        NotificationManagerCompat.from(context)
            .createNotificationChannel(channel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createLowPriorityChannel(context: Context) {
        val name = NOTICE
        val channelDescription = NOTICES
        val priority = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(
            LOW_PRIORITY_CHANNEL,
            name,
            priority
        ).apply {
            description = channelDescription
        }
        NotificationManagerCompat.from(context)
            .createNotificationChannel(channel)
    }
}