package com.ski.ski_4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Alyosha on 06.10.2014.
 */
public final class Storage {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public Storage() {}


    /* Inner class that defines the table contents */
    public static abstract class Table implements BaseColumns {
        public static final String TABLE_NAME = "table";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_DATE1 = "date1";
        public static final String COLUMN_NAME_DATE2 = "date2";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_NUMBER = "number";

    }


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Table.TABLE_NAME + " (" +
                    Table._ID + " INTEGER PRIMARY KEY," +
                    Table.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    Table.COLUMN_NAME_PRICE + TEXT_TYPE + COMMA_SEP +
                    Table.COLUMN_NAME_DATE1 + TEXT_TYPE + COMMA_SEP +
                    Table.COLUMN_NAME_DATE2 + TEXT_TYPE + COMMA_SEP +
                    Table.COLUMN_NAME_PHONE + TEXT_TYPE + COMMA_SEP +
                    Table.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    Table.COLUMN_NAME_NUMBER + TEXT_TYPE + COMMA_SEP +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Table.TABLE_NAME;

    public class StorageDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        public StorageDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }



}
