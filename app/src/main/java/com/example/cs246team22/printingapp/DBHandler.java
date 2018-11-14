package com.example.cs246team22.printingapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class DBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "spool.db";
    public static final String TABLE_NAME = "Spool";
    public static final String COLUMN_ID = "SpoolID";
    public static final String COLUMN_NAME = "ManufacturerName";
    //initialize the database
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "(" + COLUMN_ID +
                "INTEGER PRIMARYKEY," + COLUMN_NAME + "TEXT )";
        db.execSQL(CREATE_TABLE);
    }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public String loadHandler() {
        String result = "";
        String query = "Select * FROM" + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    public void addHandler(Spool spool) public void addHandler(Spool spool) {
    ContentValues values = new ContentValues();
    values.put(COLUMN_ID, spool.getID());
    values.put(COLUMN_NAME, spool.manufacturerName());
    SQLiteDatabase db = this.getWritableDatabase();
    db.insert(TABLE_NAME, null, values);
    db.close();
}
    public Spool findHandler(String manufacturer) {}
    public boolean deleteHandler(int ID) {}
    public boolean updateHandler(int ID, String name) {}


    }
}