package com.ski.ski_4;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: kvrwa_000
 * Date: 11/1/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class SendDataCheck extends AsyncTask<String, Void, String> {


    /*private   String[] Names;
    private   String[] Prices;
*/
    /*public String[] getStr() {
   return Names ;

    }*/

    private static ArrayList<String> ls = new ArrayList<String>(5);

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

        context.startActivity(new Intent(context, Skipass_info.class));


    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }


    private Context context;
    ProgressDialog dialog;


    public SendDataCheck(Context cxt) {
        super();
        this.context = cxt;

    }

    public void requestURL() {


        Document doc;
        try {
            // need http protocol
            doc = Jsoup.connect("http://tickets.bukovel.com/?NumTicket=" + Check.getNumber()).get();
            Log.w("Ski_c Number = ", Check.getNumber());
            String str = doc.body().text();
            System.out.println(str);
            for (Element element : doc.select("strong"))    // Select all 'p'-Tags and loop over them
            {
                if (element.hasText())                 // Check if the element has text (since there are some empty too)
                {
                    System.out.println(element.text()); // print the element's text
                    ls.add(element.text());
                    Log.w("Ski_c element.text():", element.text());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String GetNumber() {

        if (ls.size() == 0) return null;
        else return ls.get(3);
    }

    public static String GetDays() {
        if (ls.size() == 0) return null;
        return ls.get(1);
    }

    public static String GetSellDate() {
        if (ls.size() == 0) return null;
        return ls.get(2);
    }

    public static String GetUsedInput() {
        if (ls.size() == 0) return null;
        return ls.get(4);
    }


}
