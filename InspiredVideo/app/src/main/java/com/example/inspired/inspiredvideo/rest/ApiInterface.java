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
    @GET("?s=Batman&page=1")
    Call<MoviesResponse> getMoviesDrama();

    @GET("?s=Comedy&page=1")
    Call<MoviesResponse> getMoviesComedy();

    @GET("?s=Batman&page=1")
    Call<MoviesResponse> getMoviesAll();

    @GET("?i={id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id);
}
