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
        populateList();
        listviewAdapter adapter;
        if(list!=null)
            adapter = new listviewAdapter(this, list);
        else
            adapter=new listviewAdapter(this,new ArrayList<HashMap>());
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

        }         );

    }

    public static int getPos(){
        return listpos;
    }

    public static String getPrice(){
        return pr;
    }
    public static String getPhone(){
        return ph;
    }

    public static String getName(){
        return n;
    }

    public static String getDate1(){
        return d1;
    }

    public static String getDate2(){
        return d2;
    }

    private static String pr = null;
    private static String ph = null;
    private static String n = null;
    private static String d1 = null;
    private static String d2 = null;


    private void populateList() {


        //HashMap temp = new HashMap();

        //List<Map<String, String>> listOfTemp = new ArrayList<Map<String, String>>(SendData.arraylength);
        List<HashMap> listOfTemp = new ArrayList<HashMap>(SendData.arraylength);

        list = new ArrayList<HashMap>();
        Log.w("Ski_c adding to Hashmap size =", String.valueOf(SendData.arraylength));
        for (int i=0; i<SendData.arraylength; i++){




            //temp.put(FIRST_COLUMN, Constants.Names.get(i).toString());
            listOfTemp.get(i).put(FIRST_COLUMN, Constants.Names.get(i).toString());
            Log.w("Ski_c adding to Hashmap",Constants.Prices.get(i).toString() );
            //temp.put(SECOND_COLUMN, Constants.Prices.get(i).toString());
            listOfTemp.get(i).put(SECOND_COLUMN, Constants.Prices.get(i).toString());
            pr=Constants.Prices.get(i).toString();
            ph=Constants.Phones.get(i).toString();
            n=Constants.Names.get(i).toString();
            d1=Constants.Date1.get(i).toString();
            d2=Constants.Date2.get(i).toString();

            //list.add(temp);
            list.add((HashMap) listOfTemp.get(i));



        }


        /*HashMap temp = new HashMap();
        temp.put(FIRST_COLUMN,"Colored Notebooks");
        temp.put(SECOND_COLUMN, String.valueOf(listpos));
      *//*  temp.put(THIRD_COLUMN, "Rs. 200");
        temp.put(FOURTH_COLUMN, "Per Unit");*//*
        list.add(temp);*/


        /*HashMap temp1 = new HashMap();
        temp1.put(FIRST_COLUMN,"Colored Notebooks");
        temp1.put(SECOND_COLUMN, String.valueOf(listpos));
      *//*  temp.put(THIRD_COLUMN, "Rs. 200");
        temp.put(FOURTH_COLUMN, "Per Unit");*//*
        list.add(temp1);*/


       /* HashMap temp1 = new HashMap();
        temp1.put(FIRST_COLUMN,"Diaries");
        temp1.put(SECOND_COLUMN, "By Amee Products");
        *//*temp1.put(THIRD_COLUMN, "Rs. 400");
        temp1.put(FOURTH_COLUMN, "Per Unit");*//*

        list.add(temp1);



        HashMap temp2 = new HashMap();
        temp2.put(FIRST_COLUMN,"Note Books and Stationery");
        temp2.put(SECOND_COLUMN, "By National Products");
       *//* temp2.put(THIRD_COLUMN, "Rs. 600");
        temp2.put(FOURTH_COLUMN, "Per Unit");*//*

        list.add(temp2);


        HashMap temp3 = new HashMap();
        temp3.put(FIRST_COLUMN,"Corporate Diaries");
        temp3.put(SECOND_COLUMN, "By Devarsh Prakashan");
        *//*temp3.put(THIRD_COLUMN, "Rs. 800");
        temp3.put(FOURTH_COLUMN, "Per Unit");*//*

        list.add(temp3);



        HashMap temp4 = new HashMap();
        temp4.put(FIRST_COLUMN,"Writing Pad");
        temp4.put(SECOND_COLUMN, "By TechnoTalaktive Pvt. Ltd.");
        *//*temp4.put(THIRD_COLUMN, "Rs. 100");
        temp4.put(FOURTH_COLUMN, "Per Unit");*//*

        list.add(temp4);*/

    }

    public void GoBack(View view) {
        this.finish();

    }

}