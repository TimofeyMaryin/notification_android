# NOTIFICATION

**Create simple Notification**

<br>

## First step: CREATE NOTIFICATION CHANNEL

```
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
```
`context.getString(R.string.channel_name)` - **any name for channel from project string XML files**
`context.getString(R.string.channel_desctiption)` - **any nadescription for channel from project string XML files**
` NotificationChannel(channelId, name, importance).apply { description = descriptionText }` - **creating channel**

<br>

## Second step: CREATE BASE STRUKTURE FOR NOTIFICATION
```
private val builder = NotificationCompat.Builder(context, channelId)
```

<br>

## Third step: CREATE NOTIFICATION
```
    fun createSimpleNotification() {
        createNotificationChannel()
        val notify = builder
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("This content title")
            .setContentText("This is a content text")
            .setSubText("this is a sub text")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(notificationId, notify)
    }
```

<br>
<br>

## Example: Create Notificaion with large text
```
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
```

Fow more info you can see [Documentation](https://developer.android.com/develop/ui/views/notifications/build-notification)
