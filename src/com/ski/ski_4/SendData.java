package com.ski.ski_4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;
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


/**
 * Created with IntelliJ IDEA.
 * User: kvrwa_000
 * Date: 11/1/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class SendData extends AsyncTask<String, Void, String> {

    NotificationManager nm1;

    public static int arraylength;

    private String Error = null;

    private ArrayList<String> name1;
    private ArrayList<String> phone1;
    private ArrayList<String> price1;
    private ArrayList<String> date11;
    private ArrayList<String> date21;

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

        /*if (Integer.parseInt(ChooseDate.getYearFromDatePicker1())>Integer.parseInt(ChooseDateEnd.getYearFromDatePicker2())&&Integer.parseInt(ChooseDate.getMonthFromDatePicker1())>Integer.parseInt(ChooseDateEnd.getMonthFromDatePicker2())&&Integer.parseInt(ChooseDate.getDayFromDatePicker1())>Integer.parseInt(ChooseDateEnd.getDayFromDatePicker2())) {
            Toast.makeText(, "Done", Toast.LENGTH_LONG).show();
            break;
        }
*/

    }

    @Override
    protected void onPostExecute(String result) {

        Log.w("Ski_c sd", " in onPostExecute()");

        if (Error == "3") {
            Toast.makeText(context, "No internet connection, please check your settings", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);

        }
        else if (Error == "4") {

            Toast.makeText(context, "Bukovel ski-pass service is temporary unavailable", Toast.LENGTH_LONG).show();
            /*Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);*/

        }

        else if (Error == "5") {



                Log.i("Ski_c sd ", "showNotification()");

                showNotification();


            Log.i("Ski_c sd ", "after showNotification()");
            Toast.makeText(context, "Bukovel ski-pass Service is temporary unavailable", Toast.LENGTH_LONG).show();
            /*Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);*/

        }

        else

        switch (Constants.ACTIVITY) {
            case 0:


                super.onPostExecute(result);
                Log.w("Ski_c sd ", "Dialog dismissed");
                Intent intent = new Intent(context, MultiColumnActivity.class);
                intent.putExtra("name", name1);
                Log.i("Ski_c sd name to displayFromResult pushed is", String.valueOf(name1));

                /*if (name1==null)
                Toast.makeText(context, "Service is temporary unavailable, your ad wasn't saved", Toast.LENGTH_LONG).show();
                else Toast.makeText(context, "Your information is added", Toast.LENGTH_LONG).show();*/

                intent.putExtra("price", price1);
                intent.putExtra("phone", phone1);
                Log.i("Ski_c sd phone to displayFromResult pushed is", String.valueOf(phone1));
                intent.putExtra("date1", date11);
                intent.putExtra("date2", date21);
                Log.i("Ski_c sd startActivity ", String.valueOf(intent));
                context.startActivity(intent);
                break;

            case 1:
                super.onPostExecute(result);
                Log.w("Ski_c sd ", "Dialog dismissed");
                Toast.makeText(context, "Your data was stored successfully", Toast.LENGTH_LONG).show();
                Intent intent0 = new Intent(context, MainActivity.class);
                context.startActivity(intent0);
                intent0.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                break;
            default:
                break;

        }



    }

    @Override
    protected void onCancelled() {
        Log.w("Ski_c sd ", "onCancelled()");
        dialog.dismiss();
        super.onCancelled();
    }



    public SendData(Context cxt) {
        super();
        this.context = cxt;
        nm1 = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    }

    public void requestURL() {



        String idbuyt = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        String price, date1, date2, phone, name, number;

        HttpClient client = new DefaultHttpClient();
        HttpPost post;
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        clearCnst();

        Log.i("Ski_c SD Constants.ACTIVITY is", String.valueOf(Constants.ACTIVITY));
        switch (Constants.ACTIVITY) {
            case 0:
                post = new HttpPost("http://54.72.24.156:8080/ski2/buy");
                date1 = ChooseDate.getYearFromDatePicker1() + "-" + ChooseDate.getMonthFromDatePicker1() + "-" + ChooseDate.getDayFromDatePicker1();
                date2 = ChooseDateEnd.getYearFromDatePicker2() + "-" + ChooseDateEnd.getMonthFromDatePicker2() + "-" + ChooseDateEnd.getDayFromDatePicker2();
                nameValuePairs.add(new BasicNameValuePair("date1", date1));
                nameValuePairs.add(new BasicNameValuePair("date2", date2));
                Log.w("Ski_c sd ", "info added to buy");
                break;
            case 1:
                post = new HttpPost("http://54.72.24.156:8080/ski2/sell");
                price = EnterPrice.getPrice();
                date1 = ChooseDate.getYearFromDatePicker1() + "-" + ChooseDate.getMonthFromDatePicker1() + "-" + ChooseDate.getDayFromDatePicker1();
                date2 = ChooseDateEnd.getYearFromDatePicker2() + "-" + ChooseDateEnd.getMonthFromDatePicker2() + "-" + ChooseDateEnd.getDayFromDatePicker2();
                phone = EnterName.getPhone();
                name = EnterName.getName();
                /*number = Check_Fr.getNumber();*/
                //nameValuePairs.add(new BasicNameValuePair("idBuyT", idbuyt));
                nameValuePairs.add(new BasicNameValuePair("price", price));
                nameValuePairs.add(new BasicNameValuePair("date1", date1));
                nameValuePairs.add(new BasicNameValuePair("date2", date2));
                nameValuePairs.add(new BasicNameValuePair("phone", phone));
                Log.i("Ski_c sd phone added is", String.valueOf(phone));
                nameValuePairs.add(new BasicNameValuePair("name", name));
                Log.i("Ski_c sd name added is", String.valueOf(name));
                Log.w("Ski_c", "sd info added to sell");

            /*nameValuePairs.add(new BasicNameValuePair("number", number));*/
                break;
            case 2:
                post = new HttpPost("http://54.72.24.156:8080/ski2/");
                number = Check.getNumber();
                nameValuePairs.add(new BasicNameValuePair("number", number));
                break;
            default:
                post = new HttpPost("http://54.72.24.156:8080/ski2/buy");
                date1 = ChooseDate.getYearFromDatePicker1() + "-" + ChooseDate.getMonthFromDatePicker1() + "-" + ChooseDate.getDayFromDatePicker1();
                date2 = ChooseDateEnd.getYearFromDatePicker2() + "-" + ChooseDateEnd.getMonthFromDatePicker2() + "-" + ChooseDateEnd.getDayFromDatePicker2();
                nameValuePairs.add(new BasicNameValuePair("date1", date1));
                nameValuePairs.add(new BasicNameValuePair("date2", date2));
                break;
        }


        try {
            Log.i("Ski_c sd already in ", "post.setEntity");
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            long startTime = System.currentTimeMillis();
            Log.i("Ski_c sd startTime is ", String.valueOf(startTime));
            HttpResponse response = client.execute(post);
            long stopTime = System.currentTimeMillis();
            Log.i("Ski_c sd stopTime is ", String.valueOf(stopTime));
            long executionTime = stopTime-startTime;
            Log.i("Ski_c sd executionTime is ", String.valueOf(executionTime));

            if (executionTime>60000) {

                Error = "4";

                if (Constants.ACTIVITY==1) {

                    Error = "5";
                    Log.i("Ski_c sd executionTime", " >60000");
                }

            }


            int StatusCode = response.getStatusLine().getStatusCode();    //200 is bad or ok?
            Log.w("Ski_c sd StatusCode is ", String.valueOf(StatusCode));

            Log.w("Ski_c sd", " info sent");
            switch (Constants.ACTIVITY) {
                case 0:
                    Log.v("response code", response.getStatusLine().getStatusCode() + "");
                    HttpEntity entity = response.getEntity();

                    String token1 = null;
                    String token2 = null;
                    String token3 = null;
                    String token4 = null;
                    String token5 = null;

                    Log.i("Ski_c sd received entity is", String.valueOf(entity));


                    if (entity != null) {
                        String retSrc = EntityUtils.toString(entity);
                        // parsing JSON
                        Log.w("Ski_c sd JSON retSrc", retSrc);
                        JSONArray array = new JSONArray();
                        JSONObject main = new JSONObject(retSrc);
                        array = main.getJSONArray("AvaleiblePasses");
                        Log.w("Ski_c sd JSONArray ", array.toString());
                        arraylength=  array.length();
                        name1 = new ArrayList<String>();
                        Log.i("Ski_c sd for json is", String.valueOf(name1));
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

                            Log.w("Ski_c sd token1 is ", token1);
                            token2 = obj.getString("Phone");
                            phone1.add(token2);
                            Constants.Phones.add(i, token2);
                            Log.w("Ski_c sd Constants.Phones.get(i).toString() is ", Constants.Phones.get(i).toString());
                            Log.w("Ski_c sd token2 is ", token2);
                            token3 = obj.getString("price");
                            price1.add(token3);
                            Constants.Prices.add(i, token3);
                            token4 = obj.getString("Date1");
                            date11.add(token4);
                            Constants.Date1.add(i, token4);
                            token5 = obj.getString("Date2");
                            date21.add(token5);
                            Constants.Date2.add(i, token5);
                            Log.w("Ski_c sd", " info added to Constants");
                        }


                    } else {

                        Log.w("Ski_c sd ", "entity = null");
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

            Log.w("Ski_c 1", "Ski_c UnsupportedEncodingException");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClientProtocolException e) {
            Log.w("Ski_c 2", "Ski_c ClientProtocolException");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            Log.w("Ski_c SD 3", "Ski_c IOException");
            Log.w("Ski_c SD context is ", String.valueOf(context));

            Error = "3";


            System.out.println("General Ski_c I/O exception: " + e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();
            Log.w("Ski_c SD 4", "JSONException");
            Error = "4";
            System.out.println("General Ski_c JSONException: " + e.getMessage());
        }

    }


    private void showNotification() {

        Log.i("Ski_c sd ", "inside showNotification");

        /*Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Log.i("Ski_c sd ", " new Intent(this, MainActivity.class)");
        PendingIntent pi = TaskStackBuilder.create(context)
                .addParentStack(SendData.class)
                .addNextIntent(resultIntent)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);*/

        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Your ski-pass was not saved!")
                .setContentText("Internet connection was not established!")
                /*.setContentIntent(pi)*/
                .setLights(0xff0000ff, 5000, 5000)
                .setAutoCancel(true)
                /*.setNumber(++numMessages)*/
                .build();




                                  //= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        Log.i("Ski_c sd ", " nm1.notify(0, notification");
        nm1.notify(0, notification);



    }


}