package com.example.inspired.inspiredvideo.data;

import com.example.inspired.inspiredvideo.utils.MovieItem;

import java.util.ArrayList;

/**
 * Created by inspired on 01.08.16.
 */
public class Context {
    public static ArrayList<MovieItem> mData = new ArrayList<>();
    public static ArrayList<MovieItem> mCurrentData = new ArrayList<>();

    public static boolean setmVideoItemsByGenre(int currentGenreId, boolean favouritesEnabled){
        ArrayList<MovieItem> movieItemsByGenre;
        ArrayList<MovieItem> temp = mData;

        // 1. The Spinner is on the first option: All.
        if(currentGenreId == 0){
            if(favouritesEnabled){
                updatemMovieItemsByFavourite();
                return true;
            }
            Context.mCurrentData = temp;
            return true;
        }

        // 2. The Spinner is with different option than All.
        if(favouritesEnabled){
            movieItemsByGenre = updateWithFavouriteEnabled(currentGenreId);
        } else {
            movieItemsByGenre = updateWithoutFavouriteEnabled(currentGenreId);
        }
        Context.mCurrentData = movieItemsByGenre;
        return true;
    }

    private static void updatemMovieItemsByFavourite(){
        MovieItem movieItem;
        ArrayList<MovieItem> movieItemsByFavourite = new ArrayList<>();
        for (int i = 0; i < Context.mData.size(); i++){
            movieItem = Context.mData.get(i);
            if(movieItem.isFavourite()){
                movieItem.setCurrentPosition(movieItemsByFavourite.size());
                movieItemsByFavourite.add(movieItem);
            }
        }

        Context.mCurrentData = movieItemsByFavourite;
    }

    private static ArrayList<MovieItem> updateWithFavouriteEnabled(int currentGenreId){
        MovieItem movieItem;
        ArrayList<MovieItem> data = new ArrayList<>();

        for (int i = 0; i < Context.mData.size(); i++){
            movieItem = Context.mData.get(i);
            if(movieItem.getMovieGenreId() == currentGenreId && movieItem.isFavourite()){
                movieItem.setCurrentPosition(data.size());
                data.add(movieItem);
            }
        }
        return data;
    }
    private static ArrayList<MovieItem> updateWithoutFavouriteEnabled(int currentGenreId){
        MovieItem movieItem;
        ArrayList<MovieItem> data = new ArrayList<>();

        for (int i = 0; i < Context.mData.size(); i++){
            movieItem = Context.mData.get(i);
            if(movieItem.getMovieGenreId() == currentGenreId){
                movieItem.setCurrentPosition(data.size());
                data.add(movieItem);
            }
        }
        return data;
    }

}
