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
        NameInput.setText(String.valueOf(MultiColumnActivity.listpos));

        TextView PhoneInput = (TextView) findViewById(R.id.PhoneInput);
        PhoneInput.setText("+380662404040");
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