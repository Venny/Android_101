package com.example.inspired.inspiredvideo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<VideoItem> mVideoItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.video_recycler_view);

        // Because we won't change the layout size.
        mRecyclerView.setHasFixedSize(true);

        // Specifying the Layout manager.
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mVideoItem.add(new VideoItem("GO6o", "ferdg"));
        mVideoItem.add(new VideoItem("Sharo", "sfdgrdg"));


        // Specifying the Adapter.
        mAdapter = new VideoAdapter(getApplicationContext(), mVideoItem);
        mRecyclerView.setAdapter(mAdapter);

    }
}
