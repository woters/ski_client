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
        listviewAdapter adapter = new listviewAdapter(this, list);
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



    private void populateList() {


        list = new ArrayList<HashMap>();


        for (int i=0; i<list.size(); i++){


        /*temp.put(FIRST_COLUMN,"Colored Notebooks");
        temp.put(SECOND_COLUMN, String.valueOf(listpos));
      *//*  temp.put(THIRD_COLUMN, "Rs. 200");
        temp.put(FOURTH_COLUMN, "Per Unit");*//*
        list.add(temp);*/
        }


        HashMap temp = new HashMap();
        temp.put(FIRST_COLUMN,"Colored Notebooks");
        temp.put(SECOND_COLUMN, String.valueOf(listpos));
      /*  temp.put(THIRD_COLUMN, "Rs. 200");
        temp.put(FOURTH_COLUMN, "Per Unit");*/
        list.add(temp);


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