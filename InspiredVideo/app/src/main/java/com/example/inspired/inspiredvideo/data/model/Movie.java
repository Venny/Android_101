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
    public String name;

    @SerializedName(PARAM_DESCRIPTION)
    public  String description;

    @SerializedName(PARAM_IMAGE_RES)
    private int imageRes;

    @SerializedName(PARAM_MOVIE_GENRE)
    private int movieGenreId;

    @SerializedName(PARAM_POSITION)
    private int position;

    @SerializedName(PARAM_IS_FAVOURITE)
    private boolean isFavourite;

    public Movie(String name, String description, int imageRes, int movieGenreId, int position) {
        this.name = name;
        this.description = description;
        this.imageRes = imageRes;
        this.movieGenreId = movieGenreId;
        this.isFavourite = false;
        this.position = position;
    }
}
