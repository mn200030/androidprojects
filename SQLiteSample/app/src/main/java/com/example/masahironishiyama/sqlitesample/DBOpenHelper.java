package com.example.masahironishiyama.sqlitesample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MasahiroNishiyama on 2015/02/07.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Sample.db";
    private static final int DB_VERSION = 1;
    private int m_writableDatabaseCount = 0;
    private static DBOpenHelper m_instance = null;

    synchronized static public DBOpenHelper getInstance( Context context ) {
        if ( m_instance == null ) {
            m_instance = new DBOpenHelper( context.getApplicationContext() );
        }
        return m_instance;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        // super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS Test ( _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , caption TEXT )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    synchronized public SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase db = super.getWritableDatabase();
        if ( db != null ) {
            ++m_writableDatabaseCount;
        }
        return db;
    }

    synchronized public void closeWritableDatabase( SQLiteDatabase database ) {
        if ( m_writableDatabaseCount > 0 && database != null ) {
            --m_writableDatabaseCount;
            if ( m_writableDatabaseCount == 0 ) {
                database.close();
            }
        }
    }
}
