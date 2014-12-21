package com.ski.ski_4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Alyosha on 03.10.2014.
 */
public class BootActivation extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Ski_c BA", "BootActivation of BackgroundService bcs");
        Intent serviceIntent = new Intent(BackgroundService.class.getName());
        context.startService(serviceIntent);

    }
}
