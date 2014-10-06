package com.ski.ski_4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Alyosha on 03.10.2014.
 */
public class BackgroundService extends Service {

    private Timer timer;

    private TimerTask updateTask = new TimerTask() {
        @Override
        public void run() {
            Log.i("Ski_c", "Timer task doing work");
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Ski_c", "Service creating");

        timer = new Timer("TweetCollectorTimer");
        timer.schedule(updateTask, 1000L, 60 * 10000L);
    }

    private void showNotification() {


        Intent resultIntent = new Intent(this, Skipass_info.class);
        PendingIntent pi = TaskStackBuilder.create(this)
                .addParentStack(MainActivity.class)
                .addNextIntent(resultIntent)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Bukovel ski-pass is on sale!")
                .setContentText("Press to check the new offer!")
                .setContentIntent(pi)
                .setLights(0xff0000ff, 5000, 5000)
                /*.setNumber(++numMessages)*/
                .build();



        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        nm.notify(0, notification);


        /*PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Notification notification = new Notification.Builder(this)
                .setContentTitle(this.getString(R.string.service_label))
                .setContentText(getResources().getString(R.string.service_started)).setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(contentIntent).build();
        mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags = notification.flags
                | Notification.FLAG_ONGOING_EVENT;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        mNotificationManager.notify(0, notification);*/

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Ski_c", "Service destroying");

        timer.cancel();
        timer = null;
    }
}
