package com.example.inspired.inspiredvideo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by inspired on 15.08.16.
 */
public class Movie {
    public static final String PARAM_TITLE = "title";
    public static final String PARAM_YEAR = "year";
    public static final String PARAM_MOVIE_GENRE = "type";
    public static final String PARAM_POSTER_PATH = "poster";
    public static final String PARAM_DESCRIPTION = "description";
    public static final String PARAM_IMAGE_RES = "imageRes";
    public static final String PARAM_POSITION = "position";
    public static final String PARAM_IS_FAVOURITE = "isFavourite";

    @SerializedName(PARAM_TITLE)
    private String title;

    @SerializedName(PARAM_YEAR)
    private String year;

    @SerializedName(PARAM_POSTER_PATH)
    private String poster;

    @SerializedName(PARAM_MOVIE_GENRE)
    private int type;



    @SerializedName(PARAM_DESCRIPTION)
    private  String description;

    @SerializedName(PARAM_IMAGE_RES)
    private int imageRes;

    @SerializedName(PARAM_POSITION)
    private int position;

    @SerializedName(PARAM_IS_FAVOURITE)
    private boolean isFavourite;

    private int currentPosition;

    public Movie(String title, String description, int imageRes, int movieGenreId, int position) {
        this.title = title;
        this.type = movieGenreId;

        this.description = description;
        this.imageRes = imageRes;
        this.isFavourite = false;
        this.position = position;
        this.currentPosition = position;
    }

    public Movie(String title, String year, String poster,int movieGenreId) {
        this.title = title;
        this.year = year;
        this.poster = poster;
        this.type = movieGenreId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public int getImageRes() {
        return imageRes;
    }

    public int getType() {
        return type;
    }

    public int getPosition() {
        return position;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public void setIsFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
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
}
