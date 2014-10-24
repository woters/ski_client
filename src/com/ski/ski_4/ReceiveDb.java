package com.ski.ski_4;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by Alyosha on 19.10.2014.
 */
public class ReceiveDb extends AsyncTask<String, Void, String> {

    private Exception exception;

    /*Thread.currentThread().setContextClassLoader(getClass().getClassLoader());*/

    public static InputStream getEntity() throws IOException {
        InputStream isr = null;

        Log.i("Ski_c Rdb", " inside getEntity()");

        try {

            Log.i("Ski_c Rdb", " inside try");

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://54.72.24.156:8080/ski2/copy");// your ip address  and php file name here
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();



            isr = entity.getContent();

            DisplayHelper.instr = isr;

            Log.i("Ski_c Rdb", " Connected to the server and got entity");

            Log.i("Ski_c Rdb entity isr is ", String.valueOf(isr));





    } catch (UnsupportedEncodingException e) {
        Log.w("Ski_c 1", "Ski_c UnsupportedEncodingException");
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } catch (ClientProtocolException e) {
        Log.w("Ski_c 2", "Ski_c ClientProtocolException");
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } catch (IOException e) {
        Log.w("Ski_c 3", "Ski_c IOException");
        System.out.println("General Ski_c I/O exception: " + e.getMessage());
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }

        Log.i("Ski_c Rdb ", "return isr");
        return isr;

    }

    @Override
    protected String doInBackground(String... params) {

        try {
            getEntity();
        } catch (Exception  e) {
            this.exception = e;
            Log.w("Ski_c Rdb Exception e is ", String.valueOf(e));
            /*return null;*/
        }

        return null;
    }

    /*private final Context myContext;

    @Override
    protected void onPreExecute(Context context){
        this.myContext = DbHelper.myContext;
    }*/
    /*@Override
    protected void onPostExecute(InputStream isr) {
        DbHelper.instr = isr;

    }*/

}
