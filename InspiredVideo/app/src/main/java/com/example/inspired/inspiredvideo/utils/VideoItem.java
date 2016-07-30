package com.example.inspired.inspiredvideo.utils;

import java.io.Serializable;

/**
 * Created by inspired on 12.07.16.
 */
public class VideoItem implements Serializable {
    private String name;
    private int position;
    private String description;
    private int imageRes;
    private int movieGenreId;

    public VideoItem(String name, String description, int imageRes, int position, int movieGenreId) {
        this.name = name;
        this.description = description;
        this.imageRes = imageRes;
        this.position = position;
        this.movieGenreId = movieGenreId;
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

    public int getPosition() {
        return position;
    }

    public int getMovieGenreId() {
        return movieGenreId;
    }
}
