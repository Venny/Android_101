package com.example.inspired.inspiredvideo.data.sqlite;

import android.content.Context;

import com.example.inspired.inspiredvideo.data.model.Movie;

/**
 * Created by inspired on 15.08.16.
 */
public class MovieContentProvider {
    private static final String TABLE_NAME = Movie.class.getName();

    public static final String COLUMN_NAME = Movie.PARAM_NAME;
    public static final String COLUMN_DESCRIPTION = Movie.PARAM_DESCRIPTION;
    public static final String COLUMN_IMAGE_RES = Movie.PARAM_IMAGE_RES;
    public static final String COLUMN_MOVIE_GENRE = Movie.PARAM_MOVIE_GENRE;
    public static final String COLUMN_POSITION = Movie.PARAM_POSITION;
    public static final String COLUMN_IS_FAVOURITE = Movie.PARAM_IS_FAVOURITE;

    // Create Statement for Products Table
    public static final String CREATE_TABLE_MOVIES = "CREATE TABLE "
            + TABLE_NAME + "  (" +
                DatabaseHelper.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_IMAGE_RES + " TEXT" +
                COLUMN_MOVIE_GENRE + " TEXT" +
                COLUMN_POSITION + " INTEGER" +
                COLUMN_IS_FAVOURITE + " INTEGER" +
            ");";

    private final DatabaseHelper mDatabaseHelper;

    public MovieContentProvider(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
    }
}
