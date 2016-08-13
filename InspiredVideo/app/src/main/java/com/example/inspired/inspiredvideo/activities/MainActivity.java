package com.example.inspired.inspiredvideo.activities;

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
import com.example.inspired.inspiredvideo.app.MovieAdapter;
import com.example.inspired.inspiredvideo.fragments.MoviesList;
import com.example.inspired.inspiredvideo.utils.MovieItem;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    private RecyclerView mRecyclerView;
    private MovieAdapter videoAdapter;
    private boolean favouritesEnabled = false;
    private int currentGenre = 0;
    private boolean mToggleLayout = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if(findViewById(R.id.fragment_container) != null){
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if(savedInstanceState != null){
                return;
            }
        }

        int img1 = R.drawable.lady;
        int img2 = R.drawable.biliard;
        Context.mData.add(new MovieItem("Ghostbusters", "Following a ghost invasion of Manhattan, paranormal enthusiasts Erin Gilbert and Abby Yates, nuclear engineer Jillian...", img1, 2, Context.mData.size()));
        Context.mData.add(new MovieItem("Star Trek Beyond", "The USS Enterprise crew explores the furthest reaches of uncharted space, where they ...", img2, 2, Context.mData.size()));
        Context.mData.add(new MovieItem("The Legend of Tarzan", "Tarzan, having acclimated to life in London, is called back to his former home in the jungle to ...", img1, 3, Context.mData.size()));
        Context.mData.add(new MovieItem("The Secret Life of Pets", "sfdgrdg", img2, 1, Context.mData.size()));
        Context.mData.add(new MovieItem("Me Before You", "A girl in a small town forms an unlikely bond with a recently-paralyzed man she's taking care of.\n", img1, 3, Context.mData.size()));
        Context.mCurrentData.addAll(Context.mData);

        // Creating dynamically the fragment.
        MoviesList firstFragment = MoviesList.newInstance("");

        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.fragment_container, firstFragment)
                                   .addToBackStack(null)
                                   .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu_layout, menu);

        final MenuItem spinnerBtn = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(spinnerBtn);

        // Add a dropdown menu to the Spinner.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.toolbar_spinner_items_array, R.layout.toolbar_spinner_item);
        adapter.setDropDownViewResource(R.layout.toolbar_spinner_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mRecyclerView = (RecyclerView) findViewById(R.id.video_recycler_view);
        if(videoAdapter == null){
            assert mRecyclerView != null;
            videoAdapter = (MovieAdapter) mRecyclerView.getAdapter();
        }

        switch (item.getItemId()){
            case R.id.toggle_button:
                onClickToggle();
                return true;
            case R.id.filter_favourite:
                onClickFavouriteButton(item);
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(videoAdapter == null){
            videoAdapter = (MovieAdapter) mRecyclerView.getAdapter();
        }
        Context.setmVideoItemsByGenre(position);
        currentGenre = position;
        favouritesEnabled = false;
        videoAdapter.updatemVideoItems(Context.mCurrentData, favouritesEnabled);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if(videoAdapter == null){
            videoAdapter = (MovieAdapter) mRecyclerView.getAdapter();
        }
        videoAdapter.updatemVideoItems(Context.mCurrentData, false);
    }

    private void onClickToggle(){
        RecyclerView.LayoutManager mLinearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        RecyclerView.LayoutManager mGridLayoutManager = new GridLayoutManager(mRecyclerView.getContext(), 2);

        if (mToggleLayout) {
            mRecyclerView.setLayoutManager(mGridLayoutManager);
            mToggleLayout = false;
        } else {
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mToggleLayout = true;
        }
    }

    private void onClickFavouriteButton(MenuItem item){
        if (favouritesEnabled) {
            Context.setmVideoItemsByGenre(currentGenre);
            item.setIcon(R.drawable.ic_favorite_border_white_24dp);
            favouritesEnabled = false;
        } else {
            Context.setmVideoItemsByFavourite();
            item.setIcon(R.drawable.ic_favorite_white_24dp);
            favouritesEnabled = true;
        }
        videoAdapter.setmFavouriteMenuItem(item);
        videoAdapter.updatemVideoItems(Context.mCurrentData, favouritesEnabled);
    }

    private void buildSpinnerItem(MenuItem item){

    }
}
