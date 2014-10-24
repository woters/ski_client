package com.ski.ski_4;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Alyosha on 24.10.2014.
 */
public class DisplayAllData  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("Ski_c DAD", "try onCreate");

        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);*/
        Log.i("Ski_c DAD", "try DisplayHelper.receiveEntity()");

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(0);
        try {

            Log.i("Ski_c DAD", "inside of try DisplayHelper.receiveEntity()");
            DisplayHelper dh = new DisplayHelper(this);
            dh.receiveEntity();
        } catch (IOException e) {
            Log.i("Ski_c DAD DisplayHelper IOException is", String.valueOf(e));
        }
    }
}
