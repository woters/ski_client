package com.example.ski_4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

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