package com.example.ski_4;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Alyosha on 07.10.2014.
 */
public class DbHelper extends SQLiteOpenHelper {

    // The Android's default system path of your application database.
    /*private static String DB_PATH = "/data/data/com.example.sqltest/databases/";*/

    private static String DB_NAME = "test.sqlite";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    /**`enter code here`
     * Constructor Takes and keeps a reference of the passed context in order to
     * access to the application assets and resources.
     *
     * @param context
     */
    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     * */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            // do nothing - database already exist
            Log.i("Ski_c", "database already exist");

        } else {

            // By calling this method and empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.
            this.getReadableDatabase();

            this.close();

            try {

                /*copyDataBase();*/
                copyDb();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = /*DB_PATH + */DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            // database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     * */
    /*private void copyDataBase() throws IOException {

        // Open your local db as the input stream
        //
        //Need to change input stream to servers db
        //
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        //
        //


        // Path to the just created empty db
        String outFileName = *//*DB_PATH + *//*DB_NAME;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

// Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

        Log.i("Ski_c", "database is  copied");

    }*/

    public void copyDb() throws IOException {
        String result = "";
        InputStream isr = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://your_ip_address/php_filename.php");// your ip address  and php file name here
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            isr = entity.getContent();
        } catch (Exception e) {
            Log.e("Ski_c", "Error in http connection " + e.toString());
            /*tv.setText("Couldnt connect to database");*/
        }
//convert response to string

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(isr, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result = sb.toString();
        } catch (Exception e) {
            Log.e("Ski_c", "Error  converting result " + e.toString());
        }

        //parse json data
        try {
            String s = "";
            JSONArray jArray = new JSONArray(result);

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json = jArray.getJSONObject(i);
                s = s +
                        "ID : " + json.getString("CITY_ID") + "\n" +

                        "NAME : " + json.getString("CITY_NAME") + "\n\n";
            }
            Log.i("Ski_c s is", s);
            /*tv.setText(s);*/

        } catch (Exception e) {
// TODO: handle exception
            Log.e("Ski_c", "Error Parsing Data " + e.toString());
        }
    }

    public void openDataBase() throws SQLException {

        // Open the database
        String myPath = /*DB_PATH + */DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

// Add your public helper methods to access and get content from the
// database.
// You could return cursors by doing "return myDataBase.query(....)" so it'd
// be easy
// to you to create adapters for your views.

}
