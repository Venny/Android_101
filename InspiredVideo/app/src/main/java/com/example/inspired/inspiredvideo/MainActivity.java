package com.example.inspired.inspiredvideo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLinearLayoutManager;
    private RecyclerView.LayoutManager mGridLayoutManager;
    private ArrayList<VideoItem> mVideoItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.video_recycler_view);

        // Because we won't change the layout size.
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }

        int img1 = R.drawable.lady;
        int img2 = R.drawable.biliard;
        mVideoItem.add(new VideoItem("Movie 1", "ferdg", img1));
        mVideoItem.add(new VideoItem("Movie 2", "sfdgrdg", img2));

        // Specifying the Layout manager.
        mLinearLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this, mVideoItem.size());
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        // Specifying the Adapter.
        mAdapter = new VideoAdapter(getApplicationContext(), mVideoItem);
        mRecyclerView.setAdapter(mAdapter);

    }
}
