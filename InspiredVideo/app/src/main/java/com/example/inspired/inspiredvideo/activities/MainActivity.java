package com.example.inspired.inspiredvideo.activities;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLinearLayoutManager;
    private RecyclerView.LayoutManager mGridLayoutManager;
    private boolean favouritesEnabled = false;
    private int currentGenre = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.video_recycler_view);

        // Because we won't change the layout size.
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }

        int img1 = R.drawable.lady;
        int img2 = R.drawable.biliard;
        Context.mData.add(new VideoItem("Ghostbusters", "Following a ghost invasion of Manhattan, paranormal enthusiasts Erin Gilbert and Abby Yates, nuclear engineer Jillian...", img1, 2, Context.mData.size()));
        Context.mData.add(new VideoItem("Star Trek Beyond", "The USS Enterprise crew explores the furthest reaches of uncharted space, where they ...", img2, 2, Context.mData.size()));
        Context.mData.add(new VideoItem("The Legend of Tarzan", "Tarzan, having acclimated to life in London, is called back to his former home in the jungle to ...", img1, 3, Context.mData.size()));
        Context.mData.add(new VideoItem("The Secret Life of Pets", "sfdgrdg", img2, 1, Context.mData.size()));
        Context.mData.add(new VideoItem("Me Before You", "A girl in a small town forms an unlikely bond with a recently-paralyzed man she's taking care of.\n", img1, 3, Context.mData.size()));
        Context.mCurrentData.addAll(Context.mData);

        // Specifying the Layout manager.
        mLinearLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

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
        final MenuItem spinnerBtn = menu.findItem(R.id.spinner);
        final MenuItem favouriteFilterBtn = menu.findItem(R.id.filter_favourite);
        final VideoAdapter videoAdapter = (VideoAdapter) mRecyclerView.getAdapter();
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(spinnerBtn);

        // Add an event to the Toggle button.
        toggleBtn.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            boolean mToggleLayout = true;

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (mToggleLayout) {
                    mRecyclerView.setLayoutManager(mGridLayoutManager);
                    mToggleLayout = false;
                } else {
                    mRecyclerView.setLayoutManager(mLinearLayoutManager);
                    mToggleLayout = true;
                }
                return true;
            }

            ;
        });

        favouriteFilterBtn.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (favouritesEnabled) {
                    Context.setmVideoItemsByGenre(currentGenre);
                    item.setIcon(R.drawable.ic_favorite_border_white_24dp);
                    favouritesEnabled = false;
                } else {
                    Context.setmVideoItemsByFavourite();
                    item.setIcon(R.drawable.ic_favorite_white_24dp);
                    favouritesEnabled = true;
                }
                videoAdapter.updatemVideoItems(Context.mCurrentData, favouritesEnabled);
                return true;
            }
        });

        // Add Favourite menu item instance
        videoAdapter.setmFavouriteMenuItem(favouriteFilterBtn);
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
        Context.setmVideoItemsByGenre(position);
        currentGenre = position;
        favouritesEnabled = false;
        videoAdapter.updatemVideoItems(Context.mCurrentData, favouritesEnabled);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        VideoAdapter videoAdapter = (VideoAdapter) mRecyclerView.getAdapter();
        videoAdapter.updatemVideoItems(Context.mCurrentData, false);
    }
}
