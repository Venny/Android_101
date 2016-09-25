package com.example.inspired.inspiredvideo.view.activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.data.sqlite.DatabaseHelper;
import com.example.inspired.inspiredvideo.model.Movie2;
import com.example.inspired.inspiredvideo.view.adapter.ViewPagerAdapter;
import com.example.inspired.inspiredvideo.view.fragments.MoviesListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private List<Movie2> movies = new ArrayList<Movie2>();
    private DatabaseHelper moviesDB;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.inspired_toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Tabs.
        final TabLayout.Tab all = tabLayout.newTab();
        final TabLayout.Tab comedy = tabLayout.newTab();
        final TabLayout.Tab drama = tabLayout.newTab();

        all.setText("All");
        comedy.setText("Comedy");
        drama.setText("Drama");

        tabLayout.addTab(all, 0);
        tabLayout.addTab(comedy, 1);
        tabLayout.addTab(drama, 2);

        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        if(findViewById(R.id.fragment_container) != null){
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if(savedInstanceState != null){
                return;
            }
        }

        // Creating dynamically the fragment.
       // MoviesListFragment firstFragment = MoviesListFragment.newInstance("");

        // Listen for changes in the back stack.
        //getSupportFragmentManager().addOnBackStackChangedListener(this);
        // Handle whe activity is recreated like on orientation Change.
        //shouldDisplayHomeUp();

        /*getSupportFragmentManager().beginTransaction()
                                   .add(R.id.viewpager, firstFragment)
                                   .commit();*/

        // Drawer layout.
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, /* host Activity */
                drawerLayout,  /* DrawerLayout object */
                toolbar,
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        );

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

    @Override
    protected void onPause(){
        super.onPause();
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.lady)
                .setContentTitle("Simple notification")
                .setContentText("Click me.");
        Intent resultIntent = new Intent(this, MainActivity.class);

        // Add stack builder. (helpful if we don't use the Main activity)
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());

    }
}
