package com.example.quoteofday.utils

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.quoteofday.R
import java.util.Calendar
import kotlin.random.Random

class QuoteNotificationService (
    private val context: Context,
){
    private val notificationManager=context.getSystemService(NotificationManager::class.java)

    fun showExpandableNotification(message: String) {
        val bigTextStyle = NotificationCompat.BigTextStyle()
            .bigText(message)

        val notification = NotificationCompat.Builder(context, "quote_notification")
            .setContentTitle("Quote Of Day")
            .setContentText(message)
            .setSmallIcon(R.drawable.app_icon)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setStyle(bigTextStyle)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    fun scheduleNotification(hour: Int, minute: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Set the time when the notification should trigger
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            if (timeInMillis < System.currentTimeMillis()) {
                // If the specified time is in the past, schedule it for the next day
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        // Create a PendingIntent for the BroadcastReceiver
        val intent = Intent(context, NotificationBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Schedule the alarm to trigger at the specified time every day
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }
}