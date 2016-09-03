package com.example.inspired.inspiredvideo.view.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.data.Context;
import com.example.inspired.inspiredvideo.model.Movie;
import com.example.inspired.inspiredvideo.data.sqlite.DatabaseHelper;
import com.example.inspired.inspiredvideo.model.MoviesResponse;
import com.example.inspired.inspiredvideo.rest.ApiClient;
import com.example.inspired.inspiredvideo.rest.ApiInterface;
import com.example.inspired.inspiredvideo.view.fragments.MoviesListFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    DatabaseHelper moviesDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        if(findViewById(R.id.fragment_container) != null){
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if(savedInstanceState != null){
                return;
            }
        }

        // DB helper
        // moviesDB = new DatabaseHelper(this);
        // ArrayList array_list = DatabaseHelper;
        // ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);


        int img1 = R.drawable.lady;
        int img2 = R.drawable.biliard;
       /* Context.mData.add(new Movie("Ghostbusters", "Following a ghost invasion of Manhattan, paranormal enthusiasts Erin Gilbert and Abby Yates, nuclear engineer Jillian...", img1, 2, Context.mData.size()));
        Context.mData.add(new Movie("Star Trek Beyond", "The USS Enterprise crew explores the furthest reaches of uncharted space, where they ...", img2, 2, Context.mData.size()));
        Context.mData.add(new Movie("The Legend of Tarzan", "Tarzan, having acclimated to life in London, is called back to his former home in the jungle to ...", img1, 3, Context.mData.size()));
        Context.mData.add(new Movie("The Secret Life of Pets", "sfdgrdg", img2, 1, Context.mData.size()));
        Context.mData.add(new Movie("Me Before You", "A girl in a small town forms an unlikely bond with a recently-paralyzed man she's taking care of.\n", img1, 3, Context.mData.size()));
        Context.mCurrentData.addAll(Context.mData);*/

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getMovies();
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "Numbers of movies received: " + movies.size());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // If request fails log the error.
                Log.e(TAG, t.toString());
            }
        });


        // Creating dynamically the fragment.
        MoviesListFragment firstFragment = MoviesListFragment.newInstance("");

        // Listen for changes in the back stack.
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        // Handle whe activity is recreated like on orientation Change.
        shouldDisplayHomeUp();

        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.fragment_container, firstFragment)
                                   .commit();
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, /* host Activity */
                drawerLayout,  /* DrawerLayout object */
                toolbar,
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        );
        System.out.println(drawerLayout);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu_layout, menu);
        return true;
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    private void shouldDisplayHomeUp() {
        // Enable Up button only if there are entries in the back stack
        boolean canBack = getSupportFragmentManager().getBackStackEntryCount() > 0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canBack);
    }
}
