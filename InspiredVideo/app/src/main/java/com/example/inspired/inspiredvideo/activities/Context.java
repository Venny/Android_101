package com.example.inspired.inspiredvideo.activities;

import com.example.inspired.inspiredvideo.utils.MovieItem;

import java.util.ArrayList;

/**
 * Created by inspired on 01.08.16.
 */
public class Context {
    public static ArrayList<MovieItem> mData = new ArrayList<>();
    public static ArrayList<MovieItem> mCurrentData = new ArrayList<>();

    public static void setmVideoItemsByGenre(int currentGenreId){
        MovieItem movieItem;
        ArrayList<MovieItem> movieItemsByGenre = new ArrayList<>();
        for (int i = 0; i < Context.mData.size(); i++){
            movieItem = Context.mData.get(i);
            if(currentGenreId == 0 || movieItem.getMovieGenreId() == currentGenreId){
                movieItem.setCurrentPosition(movieItemsByGenre.size());
                movieItemsByGenre.add(movieItem);
            }
        }

        Context.mCurrentData = movieItemsByGenre;
    }

    public static void setmVideoItemsByFavourite(){
        MovieItem movieItem;
        ArrayList<MovieItem> movieItemsByFavourite = new ArrayList<>();
        for (int i = 0; i < Context.mCurrentData.size(); i++){
            movieItem = Context.mCurrentData.get(i);
            if(movieItem.isFavourite()){
                movieItem.setCurrentPosition(movieItemsByFavourite.size());
                movieItemsByFavourite.add(movieItem);
            }
        }

        Context.mCurrentData = movieItemsByFavourite;
    }
}
