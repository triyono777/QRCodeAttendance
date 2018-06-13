package com.skylist.qrcodeattendance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static String NOME = "SQLITE_PRESENCA.db";
    private static  int VERSAO = 1;

    public DBHelper(Context context) {
        super(context, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE [presenca] (\n" +
                        "[id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "[nome] VARCHAR(60)  NULL,\n" +
                        "[regNumber] VARCHAR(100)  NULL,\n" +
                        "[subject] VARCHAR(60)  NULL,\n" +
                        "[url] VARCHAR(100)  NULL,\n" +
                        "[data] TIME  NULL\n" +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
