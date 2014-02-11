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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
            adapter = new listviewAdapter(this, listOfTemp1, listOfTemp2);
        else
            adapter = new listviewAdapter(this, new ArrayList<String>(), new ArrayList<String>());


        lview.setAdapter(adapter);



        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d("Ski_c", "itemClick: position = " + position);
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

    public static String getDate2(int pos) {
        return d2.get(pos);
    }



    private static ArrayList<String> pr ;
    private static ArrayList<String> ph ;
    private static ArrayList<String> n;
    private static ArrayList<String> d1;
    private static ArrayList<String> d2 ;


    private void populateList() {



        listOfTemp1 = new ArrayList<String>();
        listOfTemp2 = new ArrayList<String>();



        Intent intent = getIntent();
        ArrayList<String> name = intent.getStringArrayListExtra("name");
        ArrayList<String> price = intent.getStringArrayListExtra("price");
        n = intent.getStringArrayListExtra("name");
        pr =  intent.getStringArrayListExtra("price");
        ph = intent.getStringArrayListExtra("phone");
        d1 = intent.getStringArrayListExtra("date1");
        d2 = intent.getStringArrayListExtra("date2");

        list = new ArrayList<HashMap>();
        Log.w("Ski_c adding to Hashmap size =", String.valueOf(SendData.arraylength));
        for (int i = 0; i < SendData.arraylength; i++) {

            Log.w("Ski_c name1 =", name.get(i));   // works fine till here
            Log.w("Ski_c price", price.get(i));

            listOfTemp1.add(name.get(i));

            listOfTemp2.add(price.get(i));

        }



    }

    public void GoBack(View view) {
        this.finish();
    }

}