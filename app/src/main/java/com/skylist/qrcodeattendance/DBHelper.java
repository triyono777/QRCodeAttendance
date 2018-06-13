package com.skylist.qrcodeattendance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_POSTS =
            "CREATE TABLE " + PostContract.PostEntry.TABLE_NAME + " (" +
                    PostContract.PostEntry._ID + " INTEGER PRIMARY KEY," +
                    PostContract.PostEntry.COLUMN_NAME      + TEXT_TYPE + COMMA_SEP +
                    PostContract.PostEntry.COLUMN_REGNUMBER + TEXT_TYPE + COMMA_SEP +
                    PostContract.PostEntry.COLUMN_SUBJECT   + TEXT_TYPE + COMMA_SEP +
                    PostContract.PostEntry.COLUMN_URL       + TEXT_TYPE + COMMA_SEP +
                    PostContract.PostEntry.COLUMN_DATE      + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_POSTS =
            "DROP TABLE IF EXISTS " + PostContract.PostEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "attendanceDB.db";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_POSTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_POSTS);
        onCreate(sqLiteDatabase);
    }
}
