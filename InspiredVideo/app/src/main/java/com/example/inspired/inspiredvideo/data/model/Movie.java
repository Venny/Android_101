package com.example.inspired.inspiredvideo.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by inspired on 15.08.16.
 */
public class Movie {
    public static final String PARAM_NAME = "image";
    public static final String PARAM_DESCRIPTION = "description";
    public static final String PARAM_IMAGE_RES = "imageRes";
    public static final String PARAM_MOVIE_GENRE = "movieGenreId";
    public static final String PARAM_POSITION = "position";
    public static final String PARAM_IS_FAVOURITE = "isFavourite";

    @SerializedName(PARAM_NAME)
    private String name;

    @SerializedName(PARAM_DESCRIPTION)
    private  String description;

    @SerializedName(PARAM_IMAGE_RES)
    private int imageRes;

    @SerializedName(PARAM_MOVIE_GENRE)
    private int movieGenreId;

    @SerializedName(PARAM_POSITION)
    private int position;

    @SerializedName(PARAM_IS_FAVOURITE)
    private boolean isFavourite;

    private int currentPosition;

    public Movie(String name, String description, int imageRes, int movieGenreId, int position) {
        this.name = name;
        this.description = description;
        this.imageRes = imageRes;
        this.movieGenreId = movieGenreId;
        this.isFavourite = false;
        this.position = position;
        this.currentPosition = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImageRes() {
        return imageRes;
    }

    public int getMovieGenreId() {
        return movieGenreId;
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
}
