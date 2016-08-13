package com.example.inspired.inspiredvideo.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.app.OnItemClickListener;
import com.example.inspired.inspiredvideo.utils.MovieItem;

import java.util.ArrayList;

/**
 * Created by inspired on 12.07.16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder>{
    private ArrayList<MovieItem> mMovieItems;
    private OnItemClickListener mOnItemClickListener;

    public MovieAdapter(ArrayList<MovieItem> movieItems,
                        OnItemClickListener onItemClickListener) {
        this.mMovieItems = movieItems;
        this.mOnItemClickListener = onItemClickListener;
    }

    // Creating new view grid items.
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MovieViewHolder(vHolder);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        MovieItem movieItem = mMovieItems.get(position);
        holder.getmImageView().setImageResource(movieItem.getImageRes());
        holder.getmNameView().setText(movieItem.getName());
        holder.getmTextDescription().setText(movieItem.getDescription());

        // Adding click event for every video item.
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovieItems.size();
    }

    public void updatemVideoItems(ArrayList<MovieItem> mMovieItems) {
        this.mMovieItems = mMovieItems;
        this.notifyDataSetChanged();
    }
}
