package com.example.inspired.inspiredvideo.data.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.inspired.inspiredvideo.data.model.Movie;

/**
 * Created by inspired on 15.08.16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Movies.db";
    private static final int DATABASE_VERSION = 1;

    // For all Primary Keys _id should be used as column name
    public static final String COLUMN_ID = "_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MovieContentProvider.CREATE_TABLE_MOVIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // @TODO finish the upgrade DB method
    }
}
