package org.sirekanyan.devtools

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

private const val CHANNEL_NAME = "Main Channel"
private const val CHANNEL_ID = "main_channel"
private const val NOTIFICATION_ID = 2304

class Notifications(private val context: Application) {

    private val manager: NotificationManagerCompat =
        NotificationManagerCompat.from(context).also {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, IMPORTANCE_DEFAULT)
                it.createNotificationChannel(channel)
            }
        }

    private val notification: Notification =
        NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_build_24)
            .setContentTitle("Development is active")
            .setContentText("Swipe to deactivate")
            .setContentIntent(
                Intent(context, MainActivity::class.java).let { intent ->
                    PendingIntent.getActivity(context, 8797, intent, FLAG_IMMUTABLE)
                }
            )
            .setDeleteIntent(
                Intent(context, ResetReceiver::class.java).let { intent ->
                    PendingIntent.getBroadcast(context, 3609, intent, FLAG_IMMUTABLE)
                }
            )
            .setSilent(true)
            .build()

    fun show() {
        if (ContextCompat.checkSelfPermission(context, POST_NOTIFICATIONS) == PERMISSION_GRANTED) {
            manager.notify(NOTIFICATION_ID, notification)
        }
    }

    fun hide() {
        manager.cancel(NOTIFICATION_ID)
    }

}