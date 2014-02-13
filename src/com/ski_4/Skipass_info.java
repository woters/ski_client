package com.example.ski_4;

import android.app.Activity;
import android.os.Bundle;
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
        TextView NameInput = (TextView) findViewById(R.id.NameInput);
        NameInput.setText("");

        TextView PhoneInput = (TextView) findViewById(R.id.PhoneInput);
        PhoneInput.setText("");
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
