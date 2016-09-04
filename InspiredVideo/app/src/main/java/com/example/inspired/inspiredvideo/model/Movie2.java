package com.example.inspired.inspiredvideo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by inspired on 04.09.16.
 */
public class Movie2 {
    public static final String PARAM_TITLE = "Title";
    public static final String PARAM_YEAR = "Year";
    public static final String PARAM_IMDID = "imdbID";
    public static final String PARAM_MOVIE_GENRE = "Type";
    public static final String PARAM_POSTER_PATH = "Poster";
    public static final String PARAM_IS_FAVOURITE = "isFavourite";

    @SerializedName(PARAM_TITLE)
    private String title;

    @SerializedName(PARAM_YEAR)
    private String year;

    @SerializedName(PARAM_POSTER_PATH)
    private String poster;

    @SerializedName(PARAM_IMDID)
    private String imdbID;

    @SerializedName(PARAM_MOVIE_GENRE)
    private String type;

    @SerializedName(PARAM_IS_FAVOURITE)
    private boolean isFavourite;

    public Movie2(String title, String year, String imdbID, String type, String poster) {
        this.title = title;
        this.year = year;
        this.poster = poster;
        this.imdbID = imdbID;
        this.type = type;
        this.isFavourite = false;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String posterPath) {
        this.poster = posterPath;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
