package com.example.inspired.inspiredvideo.activities;

import com.example.inspired.inspiredvideo.utils.VideoItem;

import java.util.ArrayList;

/**
 * Created by inspired on 01.08.16.
 */
public class Context {
    protected static ArrayList<VideoItem> mData = new ArrayList<>();
    protected static ArrayList<VideoItem> mCurrentData = new ArrayList<>();

    protected static void setmVideoItemsByGenre(int currentGenreId){
        VideoItem videoItem;
        ArrayList<VideoItem> videoItemsByGenre = new ArrayList<>();
        for (int i = 0; i < Context.mData.size(); i++){
            videoItem = Context.mData.get(i);
            if(currentGenreId == 0 || videoItem.getMovieGenreId() == currentGenreId){
                videoItem.setCurrentPosition(videoItemsByGenre.size());
                videoItemsByGenre.add(videoItem);
            }
        }

        Context.mCurrentData = videoItemsByGenre;
    }

    protected static void setmVideoItemsByFavourite(){
        VideoItem videoItem;
        ArrayList<VideoItem> videoItemsByFavourite = new ArrayList<>();
        for (int i = 0; i < Context.mCurrentData.size(); i++){
            videoItem = Context.mCurrentData.get(i);
            if(videoItem.isFavourite()){
                videoItem.setCurrentPosition(videoItemsByFavourite.size());
                videoItemsByFavourite.add(videoItem);
            }
        }

        Context.mCurrentData = videoItemsByFavourite;
    }
}
