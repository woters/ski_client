package com.example.ski_4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import android.app.Activity;

import static android.support.v4.app.ActivityCompat.startActivity;


/**
 * Created with IntelliJ IDEA.
 * User: kvrwa_000
 * Date: 11/1/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class SendData extends AsyncTask<String, Void, String> {

    public static int arraylength;

    private Context context;
    ProgressDialog dialog;

    private void clearCnst(){
               Constants.Names.clear();
                Constants.Prices.clear();
                Constants.Phones.clear();
                Constants.Date1.clear();
                Constants.Date2.clear();
            }


    @Override
    protected String doInBackground(String... params) {

        requestURL();
        return null;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String result) {


        super.onPostExecute(result);
        Log.w("Ski_c", "Dialog dismissed");

        context.startActivity(new Intent(context, MultiColumnActivity.class));


    }

    @Override
    protected void onCancelled() {
        dialog.dismiss();
        super.onCancelled();
    }



   public SendData(Context cxt) {
        super();
        this.context = cxt;

    }

    public void requestURL() {



        String idbuyt = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        String price, date1, date2, phone, name, number;

        HttpClient client = new DefaultHttpClient();
        HttpPost post;
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        clearCnst();
        switch (Constants.ACTIVITY) {
            case 0:
                post = new HttpPost("http://54.203.248.89:8080/ski2/buy");
                date1 = ChooseDate.getYearFromDatePicker1() + "-" + ChooseDate.getMonthFromDatePicker1() + "-" + ChooseDate.getDayFromDatePicker1();
                date2 = ChooseDateEnd.getYearFromDatePicker2() + "-" + ChooseDateEnd.getMonthFromDatePicker2() + "-" + ChooseDateEnd.getDayFromDatePicker2();
                nameValuePairs.add(new BasicNameValuePair("date1", date1));
                nameValuePairs.add(new BasicNameValuePair("date2", date2));
                Log.w("Ski_c", "Ski_c info added to buy");
                break;
            case 1:
                post = new HttpPost("http://54.203.248.89:8080/ski2/sell");
                price = EnterPrice.getPrice();
                date1 = ChooseDate.getYearFromDatePicker1() + "-" + ChooseDate.getMonthFromDatePicker1() + "-" + ChooseDate.getDayFromDatePicker1();
                date2 = ChooseDateEnd.getYearFromDatePicker2() + "-" + ChooseDateEnd.getMonthFromDatePicker2() + "-" + ChooseDateEnd.getDayFromDatePicker2();
                phone = EnterName.getPhone();
                name = EnterName.getName();
                number = Check_Fr.getNumber();
                //nameValuePairs.add(new BasicNameValuePair("idBuyT", idbuyt));
                nameValuePairs.add(new BasicNameValuePair("price", price));
                nameValuePairs.add(new BasicNameValuePair("date1", date1));
                nameValuePairs.add(new BasicNameValuePair("date2", date2));
                nameValuePairs.add(new BasicNameValuePair("phone", phone));
                nameValuePairs.add(new BasicNameValuePair("name", name));
                Log.w("Ski_c", "Ski_c info added to sell");
            /*nameValuePairs.add(new BasicNameValuePair("number", number));*/
                break;
            case 2:
                post = new HttpPost("http://54.203.248.89:8080/ski2/");
                number = Check.getNumber();
                nameValuePairs.add(new BasicNameValuePair("number", number));
                break;
            default:
                post = new HttpPost("http://54.203.248.89:8080/ski2/buy");
                date1 = ChooseDate.getYearFromDatePicker1() + "-" + ChooseDate.getMonthFromDatePicker1() + "-" + ChooseDate.getDayFromDatePicker1();
                date2 = ChooseDateEnd.getYearFromDatePicker2() + "-" + ChooseDateEnd.getMonthFromDatePicker2() + "-" + ChooseDateEnd.getDayFromDatePicker2();
                nameValuePairs.add(new BasicNameValuePair("date1", date1));
                nameValuePairs.add(new BasicNameValuePair("date2", date2));
                break;
        }


        try {
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = client.execute(post);
            Log.w("Ski_c", "Ski_c info sent");
            switch (Constants.ACTIVITY) {
                case 0:
                    Log.v("response code", response.getStatusLine().getStatusCode() + "");
                    HttpEntity entity = response.getEntity();

                    String token1 = null;
                    String token2 = null;
                    String token3 = null;
                    String token4 = null;
                    String token5 = null;


                    if (entity != null) {
                        String retSrc = EntityUtils.toString(entity);
                        // parsing JSON
                        Log.w("Ski_c JSON retSrc", retSrc);
                        JSONArray array = new JSONArray();
                        JSONObject main = new JSONObject(retSrc);
                        array = main.getJSONArray("AvaleiblePasses");
                        Log.w("Ski_c  JSONArray ", array.toString());
                        arraylength=  array.length();
                        for (int i = 0; i < array.length(); i++) {

                            JSONObject obj = new JSONObject();
                            obj = array.getJSONObject(i);
                            Log.w("Ski_c", "received info from database");
                            token1 = obj.getString("Name");
                            Constants.Names.add(i, token1);

                            Log.w("Ski_c", token1);
                            token2 = obj.getString("Phone");
                            Constants.Phones.add(i, token2);
                            Log.w("Ski_c",Constants.Phones.get(i).toString() );
                            Log.w("Ski_c", token2);
                            token3 = obj.getString("price");
                            Constants.Prices.add(i, token3);
                            token4 = obj.getString("Date1");
                            Constants.Date1.add(i, token4);
                            token5 = obj.getString("Date2");
                            Constants.Date2.add(i, token5);
                        }


                    } else {

                        Log.w("Ski_c", "entity = null");
                    }

                    break;
                case 1:
                    break;
                case 2:
                    //возвращаем по номеру скипасса
                    break;
                default:
                    break;
            }


            //       String responseText = EntityUtils.toString(entity);
            //       System.out.println(responseText);
        } catch (UnsupportedEncodingException e) {
            Log.w("Ski_c", "Ski_c UnsupportedEncodingException");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClientProtocolException e) {
            Log.w("Ski_c", "Ski_c ClientProtocolException");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            Log.w("Ski_c", "Ski_c IOException");
            System.out.println("General Ski_c I/O exception: " + e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();
            Log.w("Ski_c", "JSONException");
            System.out.println("General Ski_c JSONException: " + e.getMessage());
        }

    }


}
