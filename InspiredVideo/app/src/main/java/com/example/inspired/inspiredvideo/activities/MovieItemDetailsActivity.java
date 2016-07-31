package com.example.inspired.inspiredvideo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.utils.VideoItem;
import com.example.inspired.inspiredvideo.utils.VideoViewHolder;

/**
 * Created by inspired on 29.07.16.
 */
public class MovieItemDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        VideoItem mVideoItem = (VideoItem) intent.getSerializableExtra("item");
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                System.out.println("here");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
