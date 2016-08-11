package com.example.inspired.inspiredvideo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.app.OnItemClickListener;
import com.example.inspired.inspiredvideo.app.MovieAdapter;

public class MoviesList extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLinearLayoutManager;


    public MoviesList() {
        // Required empty public constructor
    }

    public static MoviesList newInstance(String param1, String param2) {
        MoviesList fragment = new MoviesList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mName = getArguments().getString(ARG_NAME);
//            mId = getArguments().getInt(ARG_ID);
        }
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
        mAdapter = new MovieAdapter(com.example.inspired.inspiredvideo.activities.Context.mCurrentData, new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                MovieItemDetails nextFrag= MovieItemDetails.newInstance(position);
                System.out.println(nextFrag);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.headlines_fragment, nextFrag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        return myLayoutView;
    }

    public RecyclerView getmRecyclerView(){
        return mRecyclerView;
    }
}
