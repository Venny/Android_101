package com.example.inspired.inspiredvideo.view.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.data.Context;
import com.example.inspired.inspiredvideo.view.adapter.MovieAdapter;
import com.example.inspired.inspiredvideo.view.fragments.MoviesList;
import com.example.inspired.inspiredvideo.utils.MovieItem;

public class MainActivity extends AppCompatActivity  implements FragmentManager.OnBackStackChangedListener{
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

        // Listen for changes in the back stack.
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        // Handle whe activity is recreated like on orientation Change.
        shouldDisplayHomeUp();

        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.fragment_container, firstFragment)
                                   .commit();
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

    @Override
    public boolean onSupportNavigateUp(){
        //This method is called when the up button is pressed. Just the pop back stack.
        getSupportFragmentManager().popBackStack();
        return true;
    }
}
