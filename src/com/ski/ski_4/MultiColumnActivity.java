package com.ski.ski_4;

/**
 * Created by Alyosha on 04.12.13.
 */

/*import static com.example.ski_4.Constants.THIRD_COLUMN;
import static com.example.ski_4.Constants.FOURTH_COLUMN;*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MultiColumnActivity extends Activity

{

    private ArrayList<HashMap> list;
    public static int listpos;
    public ArrayList<String> listOfTemp1;
    public ArrayList<String> listOfTemp2;


    public void onCreate(Bundle savedInstanceState)

    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        ListView lview = (ListView) findViewById(R.id.listview);
        populateList();      //i don't know what this do      //now you know))
        listviewAdapter adapter;
        if (list != null)
        {adapter = new listviewAdapter(this, listOfTemp1, listOfTemp2);
            Log.i("Ski_c MCA ", "adapter = new listviewAdapter(this, listOfTemp1, listOfTemp2");
            Log.i("Ski_c MCA listOfTemp1 is ", String.valueOf(listOfTemp1));}
        else {
            adapter = new listviewAdapter(this, new ArrayList<String>(), new ArrayList<String>());
            Log.i("Ski_c MCA ", "adapter = new listviewAdapter(this, new ArrayList<String>(), new ArrayList<String>(");
        }


        lview.setAdapter(adapter);



        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d("Ski_c MCA", "itemClick: position = " + position);
                listpos = position;

                Intent intent = new Intent(MultiColumnActivity.this, ListPosition.class);
                startActivity(intent);


            }



        });

    }

    public static int getPos() {
        return listpos;
    }

    public static String getPrice(int pos) {
        return pr.get(pos);
    }

    public static String getPhone(int pos) {
        return ph.get(pos);
    }

    public static String getName(int pos) {
        return n.get(pos);
    }

    public static String getDate1(int pos) {
        return d1.get(pos);
    }

    public static String getDate2(int pos) { return d2.get(pos); }



    private static ArrayList<String> pr ;
    private static ArrayList<String> ph ;
    private static ArrayList<String> n;
    private static ArrayList<String> d1;
    private static ArrayList<String> d2 ;


    private void populateList() {



        listOfTemp1 = new ArrayList<String>();
        listOfTemp2 = new ArrayList<String>();



        Intent intent = getIntent();

        /*Log.i("Ski_c MCA intent is ", String.valueOf(intent));*/
        ArrayList<String> name = intent.getStringArrayListExtra("name");
        ArrayList<String> price = intent.getStringArrayListExtra("price");
        n = intent.getStringArrayListExtra("name");
        Log.i("Ski_c MCA n is ", String.valueOf(n));
        if (String.valueOf(n)=="[]") Toast.makeText(this, "There are no available ski-passes for your dates", Toast.LENGTH_LONG).show();
        pr =  intent.getStringArrayListExtra("price");
        Log.i("Ski_c MCA pr is ", String.valueOf(pr));
        ph = intent.getStringArrayListExtra("phone");
        Log.i("Ski_c MCA ph is ", String.valueOf(ph));
        d1 = intent.getStringArrayListExtra("date1");
        Log.i("Ski_c MCA d1 is ", String.valueOf(d1));
        d2 = intent.getStringArrayListExtra("date2");

        Log.i("Ski_c MCA d2 is ", String.valueOf(d2));

        list = new ArrayList<HashMap>();
        Log.w("Ski_c adding to Hashmap size =", String.valueOf(SendData.arraylength));
        for (int i = 0; i < SendData.arraylength; i++) {

            Log.w("Ski_c name1 =", name.get(i));   // works fine till here
            Log.w("Ski_c price", price.get(i));

            listOfTemp1.add(name.get(i));

            listOfTemp2.add(price.get(i));

        }



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.i("Ski_c MA", " moveTaskToBack(true);");
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void GoBack(View view) {
        this.finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//intent.putExtra("runner", Constants.ACTIVITY_SELL);

        startActivity(intent);
    }

}