package com.ski.ski_4;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Alyosha on 24.10.2014.
 */
public class DisplayHelper {

    public static InputStream instr;
    public String result;

    private ArrayList<String> name1;
    private ArrayList<String> phone1;
    private ArrayList<String> price1;
    private ArrayList<String> date11;
    private ArrayList<String> date21;

    private Context context;

    public DisplayHelper(Context cxt) {
        super();
        this.context = cxt;

    }

    public void receiveEntity() throws IOException {

        Log.i("Ski_c dh", "already in receiveEntity()");
        String result = "";


        new Thread(new Runnable() {
            @Override


            public void run() {


                try {
                    Log.i("Ski_c dh", "try to execute new Thread");
                    new ReceiveDb().execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Log.w("Ski_c Dh", "returned from ReceiveDb()");

                InputStream myInput = instr;

                Log.i("Ski_c dh entity instr is (to check if it's the same)", String.valueOf(myInput));

                try {
                    copyStreams(instr);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        }).start();


    }

    private void copyStreams(InputStream isr) throws IOException {

        Log.i("Ski_c Dh", " inside copyStreams()");

        //convert response to string
        try{
            Log.i("Ski_c Dh", " BufferedReader");
            BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"UTF-8"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result=sb.toString();
            Log.i("Ski_c Dh result string is ", result);

            displayFromResult();

            /*splitted = split(result);*//*

            addItems(*//*TABLE_NAME, *//*split(result));

            *//*Log.i("Ski_c Db the number of items is: ", String.valueOf(getContactsCount()));*//*
            Log.i("Ski_c Db all items are: ", String.valueOf(getAllContacts()));*/




        }
        catch(Exception e){
            Log.e("log_tag", "Error  converting result "+e.toString());
        }


    }

    public void displayFromResult(){
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        String token1 = null;
        String token2 = null;
        String token3 = null;
        String token4 = null;
        String token5 = null;

        /*String retSrc = EntityUtils.toString(entity);
        // parsing JSON
        Log.w("Ski_c SD JSON retSrc", retSrc);*/
        JSONArray array = new JSONArray();
        JSONObject main = null;

        Log.w("Ski_c Dh result is ", result);
        try {


            main = new JSONObject(result);


            array = main.getJSONArray("AvaleiblePasses");

            Log.w("Ski_c  JSONArray ", array.toString());
            SendData.arraylength=  array.length();
            name1 = new ArrayList<String>();
            Log.i("Ski_c SD for json is", String.valueOf(name1));
            phone1 = new ArrayList<String>();
            price1 = new ArrayList<String>();
            date11 = new ArrayList<String>();
            date21 = new ArrayList<String>();
            for (int i = 0; i < array.length(); i++) {

                JSONObject obj = new JSONObject();
                obj = array.getJSONObject(i);
                            /*Log.w("Ski_c", "received info from database");*/
                token1 = obj.getString("Name");
                name1.add(token1);
                Constants.Names.add(i, token1);

                Log.w("Ski_c dh token 1 ", token1);
                token2 = obj.getString("Phone");
                phone1.add(token2);
                Constants.Phones.add(i, token2);
                Log.w("Ski_c Dh Phones ", Constants.Phones.get(i).toString());
                Log.w("Ski_c dh token 2 ", token2);
                token3 = obj.getString("price");
                price1.add(token3);
                Constants.Prices.add(i, token3);
                token4 = obj.getString("Date1");
                date11.add(token4);
                Constants.Date1.add(i, token4);
                token5 = obj.getString("Date2");
                date21.add(token5);
                Constants.Date2.add(i, token5);
                Log.w("Ski_c Dh", " info added to Constants");

                Log.w("Ski_c Dh dates2 of Constants are ", String.valueOf(Constants.Date2));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.w("Ski_c Dh", "JSONException");
            System.out.println("General Ski_c JSONException: " + e.getMessage());
        }

        Log.i("Ski_c dh ", "Intent intent = new Intent(myContext, MultiColumnActivity.class)");
        Intent intent = new Intent(context, MultiColumnActivity.class);
        intent.putExtra("name",name1);
        Log.i("Ski_c dh name to displayFromResult pushed is", String.valueOf(name1));
        intent.putExtra("price", price1);
        intent.putExtra("phone",phone1);
        Log.i("Ski_c dh phone to displayFromResult pushed is", String.valueOf(phone1));
        intent.putExtra("date1",date11);
        intent.putExtra("date2",date21);
        Log.i("Ski_c d date21 to displayFromResult pushed is", String.valueOf(date21));
        context.startActivity(intent);

    }

}
