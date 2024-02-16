package com.example.quoteofday.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.quoteofday.utils.QuoteManager.randomQuoteNotification

class NotificationBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {

        val notificationService = QuoteNotificationService(context)
        notificationService.showExpandableNotification(randomQuoteNotification)
    }
}