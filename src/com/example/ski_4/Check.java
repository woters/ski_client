package com.example.ski_4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.zip.Inflater;

import static android.view.View.inflate;

/**
 * Created with IntelliJ IDEA.
 * User: Alyosha
 * Date: 21.11.13
 * Time: 16:59
 * To change this template use File | Settings | File Templates.
 */
public class Check extends Activity {

    static EditText editTextN;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_check);



        editTextN = (EditText) findViewById(R.id.editTextN);

    }



    public static String getNumber(){

        String number = editTextN.getText().toString();
        return number;
    }

    public void GoNext(View view) {
        Thread t = new Thread(){
            public void run(){
                new SendData().execute("");
            }
        };
        t.start();
        Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
    }



public void GoBack(View view) {
        this.finish();
    }
}
