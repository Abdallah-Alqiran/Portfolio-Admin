package com.alqiran.portfoliomainadmin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("Al-qiran", "Message received from: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            Log.d("Al-qiran", "Message data: ${remoteMessage.data}")
        }

        remoteMessage.notification?.let {
            Log.d("Al-qiran", "Message Notification Title: ${it.title}")
            Log.d("Al-qiran", "Message Notification Body: ${it.body}")
            sendNotification(it.title ?: "New Message", it.body ?: "You have a new notification")
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("Al-qiran", "Refreshed token: $token")
        
    }

    private fun sendNotification(title: String, messageBody: String) {
        val channelId = "portfolio_admin_channel"
        val notificationId = System.currentTimeMillis().toInt()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Portfolio Admin Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications for Portfolio Admin App"
            }
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notificationBuilder.build())

        Log.d("Al-qiran", "Notification sent with ID: $notificationId")
    }
}

