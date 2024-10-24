package com.example.testalarm

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.Random

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { ctx ->
            val taskTitle = intent?.getStringExtra("TASK_TITLE") ?: ""

            val notificationBuilder =
                NotificationCompat.Builder(ctx, Constants.CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("یاد آور")
                    .setContentText(taskTitle)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_ALARM)
                    .setAutoCancel(true)

            with(NotificationManagerCompat.from(ctx)) {
                if (ActivityCompat.checkSelfPermission(
                        ctx,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    notify(Random().nextInt(), notificationBuilder.build())
                }
            }

        }
    }
}