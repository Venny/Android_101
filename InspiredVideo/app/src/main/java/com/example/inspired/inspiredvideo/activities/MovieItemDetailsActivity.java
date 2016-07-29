package com.example.inspired.inspiredvideo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.utils.VideoItem;

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

    }
}
