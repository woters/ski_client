package com.ski.ski_4;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alyosha on 07.10.2014.
 */
public class DbHelper extends SQLiteOpenHelper {

    // The Android's default system path of your application database.
    /*private static String DB_PATH = "/data/data/com.example.sqltest/databases/";*/

    public static final int DATABASE_VERSION = 1;

    private static String DB_PATH = "/data/data/com.ski.ski_4/databases/";

    private static String DB_NAME = "storage";

    private static final String TABLE_NAME = "items";

    private static final String COLUMN_NAME_ID = "id";

    public static final String COLUMN_NAME_PRICE = "price";
    public static final String COLUMN_NAME_DATE1 = "date1";
    public static final String COLUMN_NAME_DATE2 = "date2";
    public static final String COLUMN_NAME_PHONE = "phone";
    public static final String COLUMN_NAME_NAME = "name";

    public static InputStream instr;

    public String result;

    private ArrayList<String> name1;
    private ArrayList<String> phone1;
    private ArrayList<String> price1;
    private ArrayList<String> date11;
    private ArrayList<String> date21;

    /*private static SQLiteDatabase myDataBase;*/

    private final Context myContext;

    /**`enter code here`
     * Constructor Takes and keeps a reference of the passed context in order to
     * access to the application assets and resources.
     *
     * @param context
     */
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     * */
    public void createDataBase() throws IOException {

        /*Log.i("Ski_c db", "already in createDataBase()");
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

                *//*copyDataBase();*//*

                *//*db.execSQL(Storage.SQL_CREATE_ENTRIES);
                Log.i("Ski_c", " Table in the db is created");*//*


                Log.i("Ski_c", " createDataBase() -> receiveEntity(); DbHelper");
                receiveEntity();



            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }*/

    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */

    private boolean checkDB(){
        boolean checkdb = false;
        try{
            String myPath = myContext.getFilesDir().getAbsolutePath().replace("files", "databases")+ File.separator + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        }
        catch(SQLiteException e){
            Log.w("Ski_c Db", "Database doesn't exist");

        }

        Log.i("Ski_c Db", "Database already exists");
        return checkdb;
    }

    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            Log.w("Ski_c db checkDataBase()", "database does't exist yet.");

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

    public void receiveEntity() throws IOException {

        Log.i("Ski_c db", "already in receiveEntity()");
        String result = "";


            new Thread(new Runnable() {
                @Override


            public void run() {


                    try {
                        Log.i("Ski_c db", "try to execute new Thread");
                        new ReceiveDb().execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    Log.w("Ski_c Db", "returned from ReceiveDb()");

                    InputStream myInput = instr;

                    Log.i("Ski_c db entity instr is (to check if it's the same)", String.valueOf(myInput));

                    try {
                        copyStreams(instr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


            }

            }).start();



    }

   /* public List<String> splitted;*/



    private void copyStreams(InputStream isr) throws IOException {

        Log.i("Ski_c Db", " inside copyStreams()");

        //convert response to string
        try{
            Log.i("Ski_c Db", " BufferedReader");
            BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"UTF-8"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result=sb.toString();
            Log.i("Ski_c Db result string is ", result);

            /*splitted = split(result);*/

            addItems(/*TABLE_NAME, */split(result));

            /*Log.i("Ski_c Db the number of items is: ", String.valueOf(getContactsCount()));*/
            Log.i("Ski_c Db all items are: ", String.valueOf(getAllContacts()));




        }
        catch(Exception e){
            Log.e("log_tag", "Error  converting result "+e.toString());
        }


    }

    private List<String> split(String result) {

        List<String> matchList = new ArrayList<String>();
        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        Matcher regexMatcher = regex.matcher(result);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // Add double-quoted string without the quotes
                matchList.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                // Add single-quoted string without the quotes
                matchList.add(regexMatcher.group(2));
            } else {
                // Add unquoted word
                matchList.add(regexMatcher.group());
            }
        }

        Log.i("Ski_c Db size of the split list is ", String.valueOf(matchList.size()));


        List<String> matchListT = new ArrayList<String>();
        int i=2;

        Log.i("Ski_c Db ", "for (int k=0 ; i < matchList.size()-2; k++)");
        for (int k=0 ; i < (matchList.size()-2); k++)
        {

            if ((k>0)&&((k % 5) == 0)) {

                i = i + 5;
                matchListT.add(matchList.get(i));

                Log.i("Ski_c Db (5) i is ", String.valueOf(i));
            } else {

                i = i + 4;
                matchListT.add(matchList.get(i));

                Log.i("Ski_c Db (4) i is ", String.valueOf(i));
            }


        }

        matchList = matchListT;

        Log.i("Ski_c Db matchList after is ", String.valueOf(matchList));

        return matchList;
    }

    private void addItems(/*TableItem tableItem, */List<String> list) {

        Log.i("Ski_c db ", "addItem");
        Log.i("Ski_c db addItem splitted list is ", String.valueOf(list));
        SQLiteDatabase db = this.getWritableDatabase();
        int i=0;

        for (int k=1;k<=list.size()/5;k++) {


            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_NAME, list.get(i)); // Contact Name
            values.put(COLUMN_NAME_PHONE, list.get(i+1)); // Contact Phone
            values.put(COLUMN_NAME_PRICE, list.get(i+2)); // Contact Name
            values.put(COLUMN_NAME_DATE1, list.get(i+3)); // Contact Phone
            values.put(COLUMN_NAME_DATE2, list.get(i+4)); // Contact Name

            // Inserting Row
            db.insert(TABLE_NAME, null, values);
            i=i+5;
        }
        db.close(); // Closing database connection
        Log.i("Ski_c db ", "values to the table are added");

        /*displayFromResult();*/
        this.close();
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

        Log.w("Ski_c Db result is ", result);
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

            Log.w("Ski_c db token 1 ", token1);
            token2 = obj.getString("Phone");
            phone1.add(token2);
            Constants.Phones.add(i, token2);
            Log.w("Ski_c DB Phones ", Constants.Phones.get(i).toString());
            Log.w("Ski_c db token 2 ", token2);
            token3 = obj.getString("price");
            price1.add(token3);
            Constants.Prices.add(i, token3);
            token4 = obj.getString("Date1");
            date11.add(token4);
            Constants.Date1.add(i, token4);
            token5 = obj.getString("Date2");
            date21.add(token5);
            Constants.Date2.add(i, token5);
            Log.w("Ski_c Db", " info added to Constants");

            Log.w("Ski_c Db dates2 of Constants are ", String.valueOf(Constants.Date2));
        }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.w("Ski_c Db", "JSONException");
            System.out.println("General Ski_c JSONException: " + e.getMessage());
        }

        Log.i("Ski_c db ", "Intent intent = new Intent(myContext, MultiColumnActivity.class)");
        Intent intent = new Intent(myContext, MultiColumnActivity.class);
        intent.putExtra("name",name1);
        Log.i("Ski_c db name to displayFromResult pushed is", String.valueOf(name1));
        intent.putExtra("price", price1);
        intent.putExtra("phone",phone1);
        Log.i("Ski_c db phone to displayFromResult pushed is", String.valueOf(phone1));
        intent.putExtra("date1",date11);
        intent.putExtra("date2",date21);
        Log.i("Ski_c db date21 to displayFromResult pushed is", String.valueOf(date21));
        myContext.startActivity(intent);

    }

    public void displayFromTable(){
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

        Log.w("Ski_c Db result is ", result);
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

                Log.w("Ski_c db token 1 ", token1);
                token2 = obj.getString("Phone");
                phone1.add(token2);
                Constants.Phones.add(i, token2);
                Log.w("Ski_c DB Phones ", Constants.Phones.get(i).toString());
                Log.w("Ski_c db token 2 ", token2);
                token3 = obj.getString("price");
                price1.add(token3);
                Constants.Prices.add(i, token3);
                token4 = obj.getString("Date1");
                date11.add(token4);
                Constants.Date1.add(i, token4);
                token5 = obj.getString("Date2");
                date21.add(token5);
                Constants.Date2.add(i, token5);
                Log.w("Ski_c Db", " info added to Constants");

                Log.w("Ski_c Db dates2 of Constants are ", String.valueOf(Constants.Date2));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.w("Ski_c Db", "JSONException");
            System.out.println("General Ski_c JSONException: " + e.getMessage());
        }

        Log.i("Ski_c db ", "Intent intent = new Intent(myContext, MultiColumnActivity.class)");
        Intent intent = new Intent(myContext, MultiColumnActivity.class);
        intent.putExtra("name",name1);
        Log.i("Ski_c db name to displayFromResult pushed is", String.valueOf(name1));
        intent.putExtra("price", price1);
        intent.putExtra("phone",phone1);
        Log.i("Ski_c db phone to displayFromResult pushed is", String.valueOf(phone1));
        intent.putExtra("date1",date11);
        intent.putExtra("date2",date21);
        Log.i("Ski_c db date21 to displayFromResult pushed is", String.valueOf(date21));
        myContext.startActivity(intent);

    }

    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Getting All Contacts
    public List<TableItem> getAllContacts() {
        List<TableItem> itemList = new ArrayList<TableItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TableItem item = new TableItem();
                item.setPhone(Integer.parseInt(cursor.getString(1)));
                item.setName(cursor.getString(0));
                item.setPrice(Integer.parseInt(cursor.getString(2)));
                item.setDate1(cursor.getString(3));
                item.setDate2(cursor.getString(4));
                // Adding contact to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        // return contact list
        return itemList;
    }



    public void openDataBase() throws SQLException {

        // Open the database
        Log.i("Ski_c db", "already in openDataBase()");
        String myPath = DB_PATH + DB_NAME;
        SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {

        /*if (myDataBase != null)
            myDataBase.close();

        super.close();*/

    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("Ski_c db", "already in onCreate()");


            checkDB();

            String CREATE_DB_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_NAME_NAME + " VARCHAR(2),"
                    + COLUMN_NAME_PHONE + " NUMBER,"
                    + COLUMN_NAME_PRICE + " NUMBER,"
                    + COLUMN_NAME_DATE1 + " VARCHAR(2),"
                    + COLUMN_NAME_DATE2 + " VARCHAR(2)" + ")";
            db.execSQL(CREATE_DB_TABLE);

            Log.i("Ski_c db DatabaseHandler", "the table is created if not existed");





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*public static DbHelper getInstance() {
        if (null == myDataBase) {

            myDataBase = new DbHelper(c);
        }
        return myDataBase;
    }*/
}

// Add your public helper methods to access and get content from the
// database.
// You could return cursors by doing "return myDataBase.query(....)" so it'd
// be easy
// to you to create adapters for your views.


