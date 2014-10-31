package com.ski.ski_4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Alyosha on 24.10.2014.
 */
public class DisplayAllData  extends Activity {

    ProgressDialog dialog;


    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        Log.i("Ski_c DAD", "try onCreate");

        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);*/
        Log.i("Ski_c DAD", "try DisplayHelper.receiveEntity()");

        dialog = new ProgressDialog(DisplayAllData.this);

        Log.w("Ski_c DAD", "Dialog 2 started");

        dialog.setTitle("LOADING...");
        dialog.setMessage("WAIT.");
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        /*Message msg = Message.obtain(this,"CANCELED");
        dialog.setCancelMessage(msg);*/
        dialog.show();
        Log.w("Ski_c DAD ", "Dialog 2 is showing");

        /*NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(0);*/
        try {

            Log.i("Ski_c DAD", "inside of try DisplayHelper.receiveEntity()");
            DisplayHelper dh = new DisplayHelper(this);
            dh.receiveEntity();
        } catch (IOException e) {
            Log.i("Ski_c DAD DisplayHelper IOException is", String.valueOf(e));

            Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show();


        }
    }




}
