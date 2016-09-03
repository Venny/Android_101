package com.example.inspired.inspiredvideo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by inspired on 03.09.16.
 */
public class MoviesResponse {
    @SerializedName("results")
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
