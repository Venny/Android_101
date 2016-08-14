package com.example.inspired.inspiredvideo.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.data.Context;
import com.example.inspired.inspiredvideo.utils.MovieItem;
import com.example.inspired.inspiredvideo.view.adapter.MovieViewHolder;

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

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_item_details, container, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);

        movieViewHolder.getmImageView().setImageResource(mMovieItem.getImageRes());
        movieViewHolder.getmNameView().setText(mMovieItem.getName());
        movieViewHolder.getmTextDescription().setText(mMovieItem.getDescription());
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.action_menu_movie_item_details, menu);
        if(mMovieItem.isFavourite()) {
            MenuItem favouriteBtn = menu.findItem(R.id.favourite_btn);
            favouriteBtn.setIcon(R.drawable.ic_favorite_white_24dp);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.favourite_btn:
                setFavourite(item);
                return true;
            case android.R.id.home:
                getFragmentManager().popBackStack();
                return true;
            default:
                return true;
        }
    }

    private void setFavourite(MenuItem item){
        if(mMovieItem.isFavourite()){
            // Update item.
            mMovieItem.setIsFavourite(false);
            item.setIcon(R.drawable.ic_favorite_border_white_24dp);
        } else{
            updateGrid(mMovieItem);
            item.setIcon(R.drawable.ic_favorite_white_24dp);
        }
    }

    private boolean updateGrid(MovieItem mMovieItem){
        mMovieItem.setIsFavourite(true);
        Context.mData.set(mMovieItem.getPosition(), mMovieItem);
        Context.mCurrentData.set(mMovieItem.getCurrentPosition(), mMovieItem);
        return true;
    }
}
