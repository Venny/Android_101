package com.example.inspired.inspiredvideo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by inspired on 03.09.16.
 */
public class MoviesResponse {
    @SerializedName("Search")
    private List<Movie2> search;
    @SerializedName("totalResults")
    private int totalResults;
    @SerializedName("Response")
    private String response;

    public List<Movie2> getResults() {
        return search;
    }

    public void setSearch(List<Movie2> results) {
        this.search = results;
    }


    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
