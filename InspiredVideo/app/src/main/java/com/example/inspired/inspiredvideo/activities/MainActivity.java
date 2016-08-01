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
    private boolean mToggleLayout = false;

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
        Context.mData.add(new VideoItem("Ghostbusters", "Following a ghost invasion of Manhattan, paranormal enthusiasts Erin Gilbert and Abby Yates, nuclear engineer Jillian...", img1,  2));
        Context.mData.add(new VideoItem("Star Trek Beyond", "The USS Enterprise crew explores the furthest reaches of uncharted space, where they ...", img2,  2));
        Context.mData.add(new VideoItem("The Legend of Tarzan", "Tarzan, having acclimated to life in London, is called back to his former home in the jungle to ...", img1, 3));
        Context.mData.add(new VideoItem("The Secret Life of Pets", "sfdgrdg", img2, 1));
        Context.mData.add(new VideoItem("Me Before You", "A girl in a small town forms an unlikely bond with a recently-paralyzed man she's taking care of.\n", img1,  3));
        Context.mCurrentData.addAll(Context.mData);

        // Specifying the Layout manager.
        mLinearLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mToggleLayout = true;

        // Specifying the Adapter.
        mAdapter = new VideoAdapter(Context.mCurrentData, new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent myIntent = new Intent(MainActivity.this, MovieItemDetailsActivity.class);
                VideoItem mVideoItem = Context.mCurrentData.get(position);
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
                if(mToggleLayout){
                    mRecyclerView.setLayoutManager(mGridLayoutManager);
                    mToggleLayout = false;
                } else {
                    mRecyclerView.setLayoutManager(mLinearLayoutManager);
                    mToggleLayout = true;
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
        VideoAdapter videoAdapter = (VideoAdapter) mRecyclerView.getAdapter();
        this.setmVideoItemsByGenre(position);
        videoAdapter.setmVideoItems(Context.mCurrentData);
        videoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        VideoAdapter videoAdapter = (VideoAdapter) mRecyclerView.getAdapter();
        videoAdapter.setmVideoItems(Context.mCurrentData);
        videoAdapter.notifyDataSetChanged();
    }

    private void setmVideoItemsByGenre(int currentGenreId){
        VideoItem videoItem;
        ArrayList<VideoItem> videoItemsByGenre = new ArrayList<>();
        for (int i = 0; i < Context.mData.size(); i++){
            videoItem = Context.mData.get(i);
            if(currentGenreId == 0 || videoItem.getMovieGenreId() == currentGenreId){
                videoItemsByGenre.add(videoItem);
            }
        }

        Context.mCurrentData = videoItemsByGenre;
    }
}
