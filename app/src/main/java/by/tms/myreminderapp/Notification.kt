package by.tms.myreminderapp

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


const val NOTIFICATION = "NOTIFICATION"

class Notification : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val intentFirst = Intent(context, MainActivity::class.java)
        val intentSecond = PendingIntent.getActivity(context, 997, intentFirst, 0)
        val deleteIntent = Intent(context, MainActivity::class.java)
        deleteIntent.action = "ru.startandroid.notifications.action_delete"
        val deletePendingIntent = PendingIntent.getService(context, 0, deleteIntent, 0)
        val notificationBuilder = context?.let {
            NotificationCompat.Builder(it, NOTIFICATION)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Время пришло")
                .setContentText("Пора за дело")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(intentSecond)
                .setDeleteIntent(intentSecond)
                .addAction(R.drawable.close_button, "Закрыть", deletePendingIntent)
                .setAutoCancel(true)


        }
        val notificationManager = context?.let { NotificationManagerCompat.from(context) }
        notificationBuilder?.build()?.let { notificationManager?.notify(999, it) }

        val ringtone = Intent(context, SoundNotification::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            context?.startForegroundService(ringtone)

        }else{
            context?.startService(ringtone)
        }
    }
}