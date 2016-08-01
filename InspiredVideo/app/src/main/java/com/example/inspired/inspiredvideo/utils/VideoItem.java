package com.example.inspired.inspiredvideo.utils;

import java.io.Serializable;

/**
 * Created by inspired on 12.07.16.
 */
public class VideoItem implements Serializable {
    private String name;
    private String description;
    private int imageRes;
    private int movieGenreId;
    private boolean isFavourite;

    public VideoItem(String name, String description, int imageRes, int movieGenreId) {
        this.name = name;
        this.description = description;
        this.imageRes = imageRes;
        this.movieGenreId = movieGenreId;
        this.isFavourite = false;
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
}
