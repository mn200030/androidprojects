package com.example.masahironishiyama.sqlitesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MasahiroNishiyama on 2015/02/07.
 */
public class TestModel {

    private SQLiteDatabase db;
    private String tableName;

    public TestModel(Context context) {
        db = DBOpenHelper.getInstance(context).getWritableDatabase();
        tableName = this.getClass().getSimpleName();
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " +  tableName + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , caption TEXT )"
        );
    }

    public void add(String caption) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("caption", caption);
        db.insert(tableName, null, contentValues);
    }

    public void update(int id, String caption) {
        ContentValues val = new ContentValues();
        val.put( "caption", caption );
        db.update( tableName, val, "_id=?", new String[] { Integer.toString( id ) });
    }

    public void drop() {
        db.execSQL("drop table " + tableName);
    }

    public List<String> findAll() {
        Cursor c = null;
        List<String> records = new ArrayList<>();

        c = db.query(tableName, new String[] {"caption"}, null,null,null,null,null);
        int length = c.getCount();
        c.moveToFirst();
        for (int i = 0; i < length ; i++) {
            records.add(c.getString(0));
            c.moveToNext();
        }
        c.close();
        return records;
    }
}
