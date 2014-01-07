package com.example.ski_4;

/**
 * Created by Alyosha on 04.12.13.
 */

import static com.example.ski_4.Constants.FIRST_COLUMN;
import static com.example.ski_4.Constants.SECOND_COLUMN;

/*import static com.example.ski_4.Constants.THIRD_COLUMN;
import static com.example.ski_4.Constants.FOURTH_COLUMN;*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class MultiColumnActivity extends Activity

{

    private ArrayList<HashMap> list;
    public static int listpos;

    public void onCreate(Bundle savedInstanceState)

    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        ListView lview = (ListView) findViewById(R.id.listview);
        populateList();      //i don't know what this do      //now you know))
        listviewAdapter adapter;
        if (list != null)
            adapter = new listviewAdapter(this, list);
        else
            adapter = new listviewAdapter(this, new ArrayList<HashMap>());
        lview.setAdapter(adapter);


        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d("Ski_c", "itemClick: position = " + position);
                listpos = position;

                Intent intent = new Intent(MultiColumnActivity.this, ListPosition.class);
//intent.putExtra("runner", Constants.ACTIVITY_SELL);
                startActivity(intent);


            }

        });

    }

    public static int getPos() {
        return listpos;
    }

    public static String getPrice() {
        return pr;
    }

    public static String getPhone() {
        return ph;
    }

    public static String getName() {
        return n;
    }

    public static String getDate1() {
        return d1;
    }

    public static String getDate2() {
        return d2;
    }

    private static String pr = null;
    private static String ph = null;
    private static String n = null;
    private static String d1 = null;
    private static String d2 = null;


    private void populateList() {


    //    List<HashMap> listOfTemp = new ArrayList<HashMap>(SendData.arraylength);
        HashMap<String, String> listOfTemp = new HashMap<String, String>();
        Intent intent = getIntent();
        ArrayList<String> name = intent.getStringArrayListExtra("name");
        ArrayList<String> price = intent.getStringArrayListExtra("price");
        ArrayList<String> phone = intent.getStringArrayListExtra("phone");
        ArrayList<String> date1 = intent.getStringArrayListExtra("date1");
        ArrayList<String> date2 = intent.getStringArrayListExtra("date2");

        list = new ArrayList<HashMap>();
        Log.w("Ski_c adding to Hashmap size =", String.valueOf(SendData.arraylength));
        for (int i = 0; i < SendData.arraylength; i++) {

            Log.w("Ski_c name1 =", name.get(i));   // works fine till here
            Log.w("Ski_c price", price.get(i));
            Log.w("Ski_c date1", date1.get(i));
            Log.w("Ski_c date2", date2.get(i));
            listOfTemp.put(FIRST_COLUMN, name.get(i));

            listOfTemp.put(SECOND_COLUMN, price.get(i));
            pr = price.get(i);
            ph = phone.get(i);
            n = name.get(i);
            d1 = date1.get(i);
            d2 = date2.get(i);

         //   list.add((HashMap) listOfTemp.get(i));
            list.add(listOfTemp);

        }


    }

    public void GoBack(View view) {
        this.finish();
    }

}