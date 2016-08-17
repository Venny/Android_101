package com.example.inspired.inspiredvideo.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inspired.inspiredvideo.data.model.Movie;

import java.util.ArrayList;

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

    public void writeMovie(Movie movie){
        SQLiteDatabase database = open();

        writeItemInDB(database, movie);

        database.close();
    }

    public void writeMovies(ArrayList<Movie> movies) {
        SQLiteDatabase database = open();

        for (Movie movie : movies) {
            writeItemInDB(database, movie);
        }
        database.close();
    }

    private ContentValues writeItemInDB(SQLiteDatabase database, Movie movie){
        final ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, movie.getName());
        values.put(COLUMN_DESCRIPTION, movie.getDescription());
        values.put(COLUMN_IMAGE_RES, movie.getImageRes());
        values.put(COLUMN_MOVIE_GENRE, movie.getMovieGenreId());
        values.put(COLUMN_POSITION, movie.getPosition());
        values.put(COLUMN_IS_FAVOURITE, movie.isFavourite());

        // Add in the DB.
        // If the success parameter returns -1, the insert method wosn't successful.
        long success = database.insert(
                TABLE_NAME,
                null, //A special parameter, can in most cases be null.
                values
        );

        return null;
    }

    public void deleteAll() {
        SQLiteDatabase db = open();

        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public void delete(String name) {
        SQLiteDatabase db = open();

        String whereClause = COLUMN_NAME + " = ?";
        String[] whereArgs = new String[]{String.valueOf(name)};

        int numRowsDeleted = db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
    }

    public ArrayList<Movie> getDogs(String nameToSearch) {

        SQLiteDatabase database = open();

        final Cursor cursor = database.query(
                // Name of the table to read from
                TABLE_NAME,

                // String array of the columns which are supposed to be read
                new String[]{COLUMN_NAME, COLUMN_AGE, COLUMN_IMAGE_URL},

                // The selection argument which specifies which row is read.
                // ? symbols are parameters.
                COLUMN_NAME + " LIKE ?",

                // The actual parameters values for the selection as a String array.
                // ? above take the value from here
                new String[]{"%" + nameToSearch + "%"},

                // GroupBy clause. Specify a column name to group similar values
                // in that column together.
                null,

                // Having clause. When using the GroupBy clause this allows you to
                // specify which groups to include.
                null,

                // OrderBy clause. Specify a column name here to order the results
                // according to that column. Optionally append ASC or DESC to specify
                // an ascending or descending order.
                null
        );


        ArrayList<Movie> movies = readMoviesFromCursor(cursor);

        database.close();

        return movies;
    }

    private ArrayList<Movie> readMoviesFromCursor(Cursor cursor) {

        int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
        int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
        int ageIndex = cursor.getColumnIndex(COLUMN_AGE);
        int imgUrlIndex = cursor.getColumnIndex(COLUMN_IMAGE_URL);

        ArrayList<Movie> movies = new ArrayList<>();

        // If moveToFirst() returns false then cursor is empty
        if (!cursor.moveToFirst()) {
            return movies;
        }

        do {
            // Read the values of a row in the table using the indexes acquired above
            long id = cursor.getLong(idIndex);
            String name = cursor.getString(nameIndex);
            int description = cursor.getInt(ageIndex);
            String value = cursor.getString(imgUrlIndex);

            movies.add(new Movie(name, description, value));
        } while (cursor.moveToNext());

        return movies;
    }


    private SQLiteDatabase open() {
        return mDatabaseHelper.getWritableDatabase();
    }
}
