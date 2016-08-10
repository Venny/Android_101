package com.example.inspired.inspiredvideo.utils;

import java.io.Serializable;

/**
 * Created by inspired on 12.07.16.
 */
public class MovieItem implements Serializable {
    private String name;
    private String description;
    private int imageRes;
    private int movieGenreId;
    private int position;
    private int currentPosition;
    private boolean isFavourite;

    public MovieItem(String name, String description, int imageRes, int movieGenreId, int position) {
        this.name = name;
        this.description = description;
        this.imageRes = imageRes;
        this.movieGenreId = movieGenreId;
        this.isFavourite = false;
        this.position = position;
        this.currentPosition = position;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageRes() {
        return imageRes;
    }

    public int getMovieGenreId() {
        return movieGenreId;
    }

    public void setIsFavourite(boolean favourite) {
        this.isFavourite = favourite;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getPosition() {
        return position;
    }
}
