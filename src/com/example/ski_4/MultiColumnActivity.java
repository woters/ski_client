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




       /* String[] Names1 = SendData.getStr();*/
        HashMap temp = new HashMap();

        Log.w("Ski_c adding to Hashmap size =", String.valueOf(Constants.Names.size()));
        for (int i=0; i<Constants.Names.size(); i++){
            list = new ArrayList<HashMap>();


            temp.put(FIRST_COLUMN, Constants.Names.get(i).toString());
            Log.w("Ski_c adding to Hashmap",Constants.Prices.get(i).toString() );
            temp.put(SECOND_COLUMN, Constants.Prices.get(i).toString());
      /*  temp.put(THIRD_COLUMN, "Rs. 200");
        temp.put(FOURTH_COLUMN, "Per Unit");*/
            list.add(temp);



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





       /* HashMap temp2 = new HashMap();
        temp2.put(FIRST_COLUMN,"Note Books and Stationery");
        temp2.put(SECOND_COLUMN, "By National Products");
       *//**//* temp2.put(THIRD_COLUMN, "Rs. 600");
        temp2.put(FOURTH_COLUMN, "Per Unit");*//**//*

        list.add(temp2);*/




    }

    public void GoBack(View view) {
        this.finish();

    }

}