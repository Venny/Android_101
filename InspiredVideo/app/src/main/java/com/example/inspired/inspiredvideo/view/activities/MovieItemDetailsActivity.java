package com.example.inspired.inspiredvideo.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.model.Movie2;
import com.example.inspired.inspiredvideo.view.adapter.MovieViewHolder;
import com.squareup.picasso.Picasso;

/**
 * Created by inspired on 08.09.16.
 */

public class MovieItemDetailsActivity extends AppCompatActivity {
    private Movie2 mMovieItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mMovieItem = (Movie2) intent.getSerializableExtra("item");
        setContentView(R.layout.activity_movie_item_details);
        View view = this.getWindow().getDecorView().findViewById(android.R.id.content);
        MovieViewHolder mVideoHolder = new MovieViewHolder(view);

        Picasso.with(mVideoHolder.getmImageView().getContext()).load(mMovieItem.getPoster()).into(mVideoHolder.getmImageView());
        mVideoHolder.getmNameView().setText(mMovieItem.getTitle());
        mVideoHolder.getmTextDescription().setText(mMovieItem.getImdbID());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu_movie_item_details, menu);
        MenuItem favouriteBtn = menu.findItem(R.id.favourite_btn);
        if(mMovieItem.isFavourite()) {
            favouriteBtn.setIcon(R.drawable.ic_favorite_white_24dp);
        }
        favouriteBtn.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(mMovieItem.isFavourite()){
                    // Update item.
                   // mMovieItem.setIsFavourite(false);
                    item.setIcon(R.drawable.ic_favorite_border_white_24dp);
                } else{
                   // updateGrid(mMovieItem);
                    item.setIcon(R.drawable.ic_favorite_white_24dp);
                }
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

   /* private boolean updateGrid(VideoItem mVideoItem){
        mVideoItem.setIsFavourite(true);
        Context.mData.set(mVideoItem.getPosition(), mVideoItem);
        Context.mCurrentData.set(mVideoItem.getCurrentPosition(), mVideoItem);
        return true;
    }*/
}
