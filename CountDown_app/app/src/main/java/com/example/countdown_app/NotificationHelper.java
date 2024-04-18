package com.example.countdown_app;

import android.app.NotificationManager;
import android.content.Context;
import android.app.NotificationChannel;

import androidx.core.app.NotificationCompat;

public class NotificationHelper {
    private static final int NOTIFICATION_ID = 1001;
    private static final String CHANNEL_ID = "EventReminderChannel";

    public static void showNotification(Context context, String title, String message) {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Event Reminders", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.bell)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
    //attribute: <a href="https://www.flaticon.com/free-icons/bell" title="bell icons">Bell icons created by Freepik - Flaticon</a>
}
