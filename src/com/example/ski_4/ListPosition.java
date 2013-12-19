package com.example.ski_4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        NameInput.setText(MultiColumnActivity.getName());

        TextView PhoneInput = (TextView) findViewById(R.id.PhoneInput);
        PhoneInput.setText(MultiColumnActivity.getPhone());

        TextView PriceInput = (TextView) findViewById(R.id.PriceInput);
        PriceInput.setText(MultiColumnActivity.getPrice());

        TextView Date1Input = (TextView) findViewById(R.id.Date1Input);
        Date1Input.setText(MultiColumnActivity.getDate1());

        TextView Date2Input = (TextView) findViewById(R.id.Date2Input);
        Date2Input.setText(MultiColumnActivity.getDate2());
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
