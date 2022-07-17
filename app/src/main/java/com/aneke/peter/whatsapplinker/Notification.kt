package com.aneke.peter.whatsapplinker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

const val CHANNEL_ID = "whatsapp_linker_notification"
const val notificationId = 4

fun ContextWrapper.makeWhatsappNotification(number: String) {
    createNotificationChannel()

    val intent =  Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(makeNigerianWhatsappUrl(number))
    }
    val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    val builder = NotificationCompat.Builder(this, CHANNEL_ID)
        .setSmallIcon(android.R.drawable.stat_sys_speakerphone)
        .setContentTitle("Phone number copied")
        .setContentText("Tap to chat with $number on whatsapp!")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    with(NotificationManagerCompat.from(this)) {
        // notificationId is a unique int for each notification that you must define
        notify(notificationId, builder.build())
    }
}


fun ContextWrapper.createNotificationChannel() {

    val name = getString(R.string.channel_name)
    val descriptionText = getString(R.string.channel_description)
    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
        description = descriptionText
    }
    // Register the channel with the system
    val notificationManager: NotificationManager =
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
}