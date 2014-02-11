package com.ski.ski_4;

import android.app.Activity;
import android.os.Bundle;
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