package com.example.inspired.inspiredvideo.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.app.VideoAdapter;
import com.example.inspired.inspiredvideo.utils.VideoItem;
import com.example.inspired.inspiredvideo.utils.VideoViewHolder;

/**
 * Created by inspired on 29.07.16.
 */
public class MovieItemDetailsActivity extends AppCompatActivity {
    private VideoItem mVideoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mVideoItem = (VideoItem) intent.getSerializableExtra("item");
        setContentView(R.layout.movie_item_details);
        View view = this.getWindow().getDecorView().findViewById(android.R.id.content);
        VideoViewHolder mVideoHolder = new VideoViewHolder(view);

        mVideoHolder.getmImageView().setImageResource(mVideoItem.getImageRes());
        mVideoHolder.getmNameView().setText(mVideoItem.getName());
        mVideoHolder.getmTextDescription().setText(mVideoItem.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu_movie_item_details, menu);
        MenuItem favouriteBtn = menu.findItem(R.id.favourite_btn);
        if(mVideoItem.isFavourite()) {
            favouriteBtn.setIcon(R.drawable.ic_favorite_white_24dp);
        }
        favouriteBtn.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(mVideoItem.isFavourite()){
                    // Update item.
                    mVideoItem.setIsFavourite(false);
                    item.setIcon(R.drawable.ic_favorite_border_white_24dp);
                } else{
                    updateGrid(mVideoItem);
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
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean updateGrid(VideoItem mVideoItem){
        mVideoItem.setIsFavourite(true);
        Context.mData.set(mVideoItem.getPosition(), mVideoItem);
        Context.mCurrentData.set(mVideoItem.getCurrentPosition(), mVideoItem);
        return true;
    }
}
