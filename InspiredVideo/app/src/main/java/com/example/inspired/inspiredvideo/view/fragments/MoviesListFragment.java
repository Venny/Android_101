package com.example.inspired.inspiredvideo.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.data.Context;
import com.example.inspired.inspiredvideo.app.OnItemClickListener;
import com.example.inspired.inspiredvideo.view.adapter.MovieAdapter;

public class MoviesListFragment extends Fragment  implements AdapterView.OnItemSelectedListener {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private boolean mToggleLayout = true;
    private MovieAdapter mMovieAdapter;
    private boolean favouritesEnabled = false;
    private int currentGenre = 0;

    public MoviesListFragment() {
        // Required empty public constructor
    }

    public static MoviesListFragment newInstance(String param1) {
        MoviesListFragment fragment = new MoviesListFragment();
        Bundle args = new Bundle();

        args.putString("someInt", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myLayoutView = inflater.inflate(R.layout.fragment_movies_list, container, false);
        mRecyclerView = (RecyclerView) myLayoutView.findViewById(R.id.video_recycler_view);

        // Because we won't change the layout size.
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }

        // Specifying the Layout manager.
        mLinearLayoutManager = new LinearLayoutManager(inflater.getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        // Specifying the Adapter.
        mMovieAdapter = new MovieAdapter(Context.mCurrentData, new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                MovieItemDetailsFragment nextFrag= MovieItemDetailsFragment.newInstance(position);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, nextFrag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        mRecyclerView.setAdapter(mMovieAdapter);
        return myLayoutView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        final MenuItem spinnerBtn = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(spinnerBtn);
        mGridLayoutManager = new GridLayoutManager(mRecyclerView.getContext(), 2);

        // Add a dropdown menu to the Spinner.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.toolbar_spinner_items_array, R.layout.toolbar_spinner_item);
        adapter.setDropDownViewResource(R.layout.toolbar_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.toggle_button:
                onClickToggleButton();
                return true;
            case R.id.filter_favourite:
                onClickFavouriteButton(item);
                return true;
            default:
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currentGenre = position;
        Context.setmVideoItemsByGenre(currentGenre, favouritesEnabled);
        mMovieAdapter.updatemVideoItems(Context.mCurrentData);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        currentGenre = 0;
        mMovieAdapter.updatemVideoItems(Context.mCurrentData);
    }

    private void onClickToggleButton(){
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
            favouritesEnabled = false;
            Context.setmVideoItemsByGenre(currentGenre, favouritesEnabled);
            item.setIcon(R.drawable.ic_favorite_border_white_24dp);
        } else {
            favouritesEnabled = true;
            Context.setmVideoItemsByGenre(currentGenre, favouritesEnabled);
            item.setIcon(R.drawable.ic_favorite_white_24dp);
        }
        mMovieAdapter.updatemVideoItems(Context.mCurrentData);
    }
}
