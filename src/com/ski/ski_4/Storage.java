package com.ski.ski_4;

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
    public static final String SQL_CREATE_ENTRIES =
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

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Table.TABLE_NAME;


}
