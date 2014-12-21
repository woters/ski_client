package com.ski.ski_4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Alyosha on 03.10.2014.
 */
public class BackgroundService extends Service {

    private Timer timer;

    private ArrayList<String> id;

    private String token = null;

    protected static int index;
    //protected static boolean service;

    private TimerTask updateTask = new TimerTask() {
        @Override
        public void run() {
            Log.i("Ski_c BcS", "Timer task doing work");

            Constants.SERVICE=1;

            Log.i("Ski_c BcS  Constants.SERVICE is ", String.valueOf(Constants.SERVICE));
            try {

                int checkIndex=checkIndex();

                index = PreferenceManager.getDefaultSharedPreferences(BackgroundService.this).getInt("index", 0);


                Log.i("Ski_c BcS before if SharedPreferences index is ", String.valueOf(index));



                //if (checkIndex>PreferenceManager.getDefaultSharedPreferences(BackgroundService.this).getInt("index", 0)){
                if (checkIndex>index){
                    Log.i("Ski_c BcS checkIndex() is ", String.valueOf(checkIndex));
                    Log.i("Ski_c BcS SharedPreferences index is ", String.valueOf(index));

                    PreferenceManager.getDefaultSharedPreferences(BackgroundService.this).edit().putInt("index", checkIndex).commit();
                    Log.i("Ski_c BcS new SharedPreferences index is ", String.valueOf(PreferenceManager.getDefaultSharedPreferences(BackgroundService.this).getInt("index", 0)));
                    showNotification();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*showNotification();*/
        }
    };

    private int checkIndex() throws IOException{

        InputStream istr;
        String result = "";

        try {

            Log.i("Ski_c BcS", " inside checkIndex() try");

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://54.72.24.156:8080/ski2/lastentry");// your ip address  and php file name here
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();



            String retSrc = EntityUtils.toString(entity);

            Log.i("Ski_c BcS JSON retSrc is", retSrc);

            JSONArray array = new JSONArray();
            JSONObject main = new JSONObject(retSrc);
            array = main.getJSONArray("AvaleiblePasses");
            Log.w("Ski_c  BcS JSONArray ", array.toString());

            JSONObject obj = new JSONObject();

            obj = array.getJSONObject(0);

             token = obj.getString("id");

            Log.i("Ski_c BcS token is", token);



        } catch (UnsupportedEncodingException e) {
            Log.w("Ski_c BcS 1", "Ski_c UnsupportedEncodingException");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClientProtocolException e) {
            Log.w("Ski_c BcS 2", "Ski_c ClientProtocolException");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            Log.w("Ski_c BcS 3", "Ski_c IOException");
            System.out.println("General Ski_c I/O exception: " + e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (token!=null){


        Log.i("Ski_c BcS return Integer.parseInt(token) is", String.valueOf(Integer.parseInt(token)));

        return Integer.parseInt(token);   }



        else Log.i("Ski_c BcS return Integer.parseInt(token) is", "null");

        return 0;

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.

        *//*Log.i("Ski_c BcS", "Service for timer creating");

        Constants.SERVICE=1;

        Log.i("Ski_c BcS Constants.SERVICE is ", String.valueOf(Constants.SERVICE));

        service = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("service", false);

        Log.i("Ski_c MA startup SharedPreferences before service is ", String.valueOf(service));

        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("service", true).commit();

        Log.i("Ski_c MA startup SharedPreferences after service is ", String.valueOf(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("service", false)));


        timer = new Timer();
        //long period = 3600000; //1 hour
        //long delay = 600000;  // 10 minutes
        long period = 300000; //5 minutes
        long delay = 30000;    //0.5 minute
        timer.scheduleAtFixedRate(updateTask, delay, period);

        Log.i("Ski_c BcS timer is scheduled every (ms) ", String.valueOf(period));*//*

        Log.i("Ski_c BcS", "Service.START_NOT_STICKY");
        return START_NOT_STICKY;
    }*/

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
        //return START_NOT_STICKY;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Ski_c BcS", "Service for timer creating");

        Constants.SERVICE=1;

        Log.i("Ski_c BcS Constants.SERVICE is ", String.valueOf(Constants.SERVICE));

        //service = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("service", false);

        //Log.i("Ski_c MA startup SharedPreferences before service is ", String.valueOf(service));

        //PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("service", true).commit();

        //Log.i("Ski_c MA startup SharedPreferences after service is ", String.valueOf(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("service", false)));


        timer = new Timer();
        //long period = 3600000; //1 hour
        long period = 600000;  // 10 minutes
        //long period = 30000; //5/10 minutes
        long delay = 20000;   //0.33 minute
        //long delay = 0;
        timer.schedule(updateTask, delay, period);

        Log.i("Ski_c BcS timer is scheduled every (ms) ", String.valueOf(period));


    }

    private void showNotification() {

        Log.i("Ski_c BcS", "showNotification");

        Intent resultIntent = new Intent(this, DisplayAllData.class);
        Log.i("Ski_c BcS", " new Intent(this, DisplayAllData.class)");
        PendingIntent pi = TaskStackBuilder.create(this)
                .addParentStack(MainActivity.class)
                .addNextIntent(resultIntent)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Bukovel ski-pass is on sale!")
                .setContentText("Press to check available offers!")
                .setContentIntent(pi)
                .setLights(0xff0000ff, 5000, 5000)
                .setAutoCancel(true)
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
        Log.i("Ski_c BcS", "Service destroying");
        //PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("service", false).commit();



        try {
            timer.cancel();
            timer = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent serviceIntent = new Intent(BackgroundService.class.getName());

        //Intent intent = new Intent("com.android.techtrainner");
        //intent.putExtra("yourvalue", "torestore");
        sendBroadcast(serviceIntent);
    }
}
