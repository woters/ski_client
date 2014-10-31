package com.ski.ski_4;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created with IntelliJ IDEA.
 * User: Alyosha
 * Date: 25.11.13
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
public class Info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//Intent intent = getIntent();

        /*MainActivity.index = MainActivity.index+1;*/

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        /*SharedPreferences prefs = <load shared preferences>;*/
        editor.putInt("MainActivity.index", MainActivity.index+1);
        editor.commit();


        Log.i("Ski_c IN index is ", String.valueOf(MainActivity.index));

        setContentView(R.layout.activity_info);

        AdView adView = (AdView)this.findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void GoToMain(View view) {
        this.finish();
//Intent intent = new Intent(this, MainActivity.class);
//startActivity(intent);
    }

}