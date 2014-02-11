package com.ski.ski_4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Alyosha on 04.12.13.
 */
public class ListPosition extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_position);
        TextView NameInput = (TextView) findViewById(R.id.NameInput);
        int i = MultiColumnActivity.getPos();
        NameInput.setText(MultiColumnActivity.getName(i));

        TextView PhoneInput = (TextView) findViewById(R.id.PhoneInput);
        PhoneInput.setText(MultiColumnActivity.getPhone(i));

        TextView PriceInput = (TextView) findViewById(R.id.PriceInput);
        PriceInput.setText(MultiColumnActivity.getPrice(i));

        TextView Date1Input = (TextView) findViewById(R.id.Date1Input);
        Date1Input.setText(MultiColumnActivity.getDate1(i));

        TextView Date2Input = (TextView) findViewById(R.id.Date2Input);
        Date2Input.setText(MultiColumnActivity.getDate2(i));

        AdView adView = (AdView)this.findViewById(R.id.adView2);
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

    public void GoBack(View view) {
        this.finish();

    }

}
