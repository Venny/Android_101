package com.example.inspired.inspiredvideo.rest;

import com.example.inspired.inspiredvideo.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by inspired on 03.09.16.
 */
public interface ApiInterface {
    @GET("?s=Batman&page=2")
    Call<MoviesResponse> getMovies();
}
