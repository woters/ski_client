package com.example.ski_4;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Alyosha on 06.12.13.
 */
public class Skipass_info extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skipass_info);
        /*TextView NameInput = (TextView) findViewById(R.id.NameInput);
        NameInput.setText("");

        TextView PhoneInput = (TextView) findViewById(R.id.PhoneInput);
        PhoneInput.setText("");*/

        TextView NumberInput = (TextView) findViewById(R.id.NumberInput);
        NumberInput.setText(SendDataCheck.GetNumber());

        TextView DaysInput = (TextView) findViewById(R.id.DaysInput);
        DaysInput.setText(SendDataCheck.GetDays());

        TextView SellDateInput = (TextView) findViewById(R.id.SellDateInput);
        SellDateInput.setText(SendDataCheck.GetSellDate());

        TextView UsedInput = (TextView) findViewById(R.id.UsedInput);
        UsedInput.setMovementMethod(new ScrollingMovementMethod());
        UsedInput.setText(SendDataCheck.GetUsedInput());

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /*public void GoToMain(View view) {
        this.finish();
//Intent intent = new Intent(this, MainActivity.class);
//startActivity(intent);
    }
*/
    public void GoBack(View view) {
        this.finish();

    }

}
