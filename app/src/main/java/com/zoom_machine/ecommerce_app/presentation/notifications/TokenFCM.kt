package com.zoom_machine.ecommerce_app.presentation.notifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object TokenFCM {
    fun getTokenFCM(scope: CoroutineScope) {
        scope.launch {
            val token = getTokenFCM()
            Log.d("NOTIFICATION", "Token: $token")
        }
    }

    private suspend fun getTokenFCM(): String? = suspendCoroutine { continuation ->
        FirebaseMessaging.getInstance().token
            .addOnSuccessListener { token ->
                continuation.resume(token)
                Log.d("NOTIFICATION", "Token: $token")
            }
            .addOnFailureListener { exception ->
                continuation.resume(null)
                Log.d("NOTIFICATION", "Token: Failure. $exception")
            }
            .addOnCanceledListener {
                continuation.resume(null)
                Log.d("NOTIFICATION", "Token: Canceled")
            }
    }
}