package com.example.inspired.inspiredvideo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.activities.Context;
import com.example.inspired.inspiredvideo.utils.MovieItem;
import com.example.inspired.inspiredvideo.utils.MovieViewHolder;

public class MovieItemDetails extends Fragment {
    private static String POSITION;

    private MovieItem mMovieItem;

    public MovieItemDetails() {
        // Required empty public constructor
    }

    public static MovieItemDetails newInstance(int moviePosition) {
        MovieItemDetails fragment = new MovieItemDetails();
        Bundle args = new Bundle();
        args.putInt(POSITION, moviePosition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovieItem = Context.mCurrentData.get(getArguments().getInt(POSITION));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_item_details, container, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        System.out.println("here");

        movieViewHolder.getmImageView().setImageResource(mMovieItem.getImageRes());
        movieViewHolder.getmNameView().setText(mMovieItem.getName());
        movieViewHolder.getmTextDescription().setText(mMovieItem.getDescription());
        return view;
    }



    private boolean updateGrid(MovieItem mMovieItem){
        mMovieItem.setIsFavourite(true);
        Context.mData.set(mMovieItem.getPosition(), mMovieItem);
        Context.mCurrentData.set(mMovieItem.getCurrentPosition(), mMovieItem);
        return true;
    }


}
