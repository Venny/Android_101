package com.example.inspired.inspiredvideo.activities;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.app.OnItemClickListener;
import com.example.inspired.inspiredvideo.app.VideoAdapter;
import com.example.inspired.inspiredvideo.utils.VideoItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLinearLayoutManager;
    private RecyclerView.LayoutManager mGridLayoutManager;
    private ArrayList<VideoItem> mVideoItems = new ArrayList<>();
    private boolean toggleLayout = false;

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
        mVideoItems.add(new VideoItem("Ghostbusters", "Following a ghost invasion of Manhattan, paranormal enthusiasts Erin Gilbert and Abby Yates, nuclear engineer Jillian...", img1, mVideoItems.size(), 2));
        mVideoItems.add(new VideoItem("Star Trek Beyond", "The USS Enterprise crew explores the furthest reaches of uncharted space, where they ...", img2, mVideoItems.size(), 2));
        mVideoItems.add(new VideoItem("The Legend of Tarzan", "Tarzan, having acclimated to life in London, is called back to his former home in the jungle to ...", img1, mVideoItems.size(), 3));
        mVideoItems.add(new VideoItem("The Secret Life of Pets", "sfdgrdg", img2, mVideoItems.size(), 1));
        mVideoItems.add(new VideoItem("Me Before You", "A girl in a small town forms an unlikely bond with a recently-paralyzed man she's taking care of.\n", img1, mVideoItems.size(), 3));

        // Specifying the Layout manager.
        mLinearLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        toggleLayout = true;

        // Specifying the Adapter.
        mAdapter = new VideoAdapter(mVideoItems, new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent myIntent = new Intent(MainActivity.this, MovieItemDetailsActivity.class);
                VideoItem mVideoItem = mVideoItems.get(position);
                myIntent.putExtra("item", mVideoItem);
                MainActivity.this.startActivity(myIntent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu_layout, menu);

        MenuItem toggleBtn = menu.findItem(R.id.toggle_button);
        MenuItem spinnerBtn = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(spinnerBtn);

        // Add an event to the Toggle button.
        toggleBtn.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(toggleLayout){
                    mRecyclerView.setLayoutManager(mGridLayoutManager);
                    toggleLayout = false;
                } else {
                    mRecyclerView.setLayoutManager(mLinearLayoutManager);
                    toggleLayout = true;
                }
                return true;
            };
        });

        // Add a dropdown menu to the Spinner.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.toolbar_spinner_items_array, R.layout.toolbar_spinner_item);
        adapter.setDropDownViewResource(R.layout.toolbar_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<VideoItem> videoItemsByGenre = null;
        VideoAdapter videoAdapter = (VideoAdapter) mRecyclerView.getAdapter();
        if(position > 0){
            videoItemsByGenre = getmVideoItemsByGenre(position);
        } else {
            videoItemsByGenre = mVideoItems;
        }
        videoAdapter.setmVideoItems(videoItemsByGenre);
        videoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        VideoAdapter videoAdapter = (VideoAdapter) mRecyclerView.getAdapter();
        videoAdapter.setmVideoItems(mVideoItems);
        videoAdapter.notifyDataSetChanged();
    }

    private ArrayList<VideoItem> getmVideoItemsByGenre(int currentGenreId){
        VideoItem videoItem;
        ArrayList<VideoItem> videoItemsByGenre = new ArrayList<>();
        for (int i = 0; i < mVideoItems.size(); i++){
            videoItem = mVideoItems.get(i);
            if(videoItem.getMovieGenreId() == currentGenreId){
                videoItemsByGenre.add(videoItem);
            }
        }

        return videoItemsByGenre;
    }
}
