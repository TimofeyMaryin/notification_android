package com.example.notification.notification_package

import android.app.Notification
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notification.MainActivity
import com.example.notification.R

class NotificationManager(private val context: Context) {
    private val channelId = "channel id"
    private val notificationId = 1

    private val builder = NotificationCompat.Builder(context, channelId)
    private val intent = Intent(context, MainActivity::class.java)
    private val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_desctiption)
            val importance = android.app.NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createSimpleNotification() {
        createNotificationChannel()
        val notify = builder
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(
                buildAnnotatedString {
                    append("Content Title")
                    addStyle(
                        style = SpanStyle(
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        ),
                        1,1
                    )
                    append("\nThis text is not refactored")
                }
            )
            .setContentText("Long text repeat 8 times ".repeat(8))
            .setSubText("this is a sub text")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(notificationId, notify)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createLongNotificationOne() {
        val largeIcon = Bitmap.createBitmap(100,100, Bitmap.Config.RGB_565)

        createNotificationChannel()
        val _builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle("New mail from Timofey")
            .setContentText("This is a content text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setLargeIcon(largeIcon)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("This is a really long text".repeat(30))
            )
            .build()

        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(234, _builder)
    }


}