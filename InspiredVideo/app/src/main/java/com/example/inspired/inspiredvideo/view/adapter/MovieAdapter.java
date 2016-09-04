package com.example.inspired.inspiredvideo.view.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.app.OnItemClickListener;
import com.example.inspired.inspiredvideo.model.Movie;
import com.example.inspired.inspiredvideo.model.Movie2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by inspired on 12.07.16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder>{
    private ArrayList<Movie2> mMovieItems;
    private OnItemClickListener mOnItemClickListener;

    public MovieAdapter(ArrayList<Movie2> movieItems,
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
        Movie2 movieItem = mMovieItems.get(position);
        URL newurl = null;
        Bitmap imageValue = null;
        try {
            newurl = new URL(movieItem.getPoster());
            //imageValue = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

       // holder.getmImageView().setImageBitmap(imageValue);
        holder.getmNameView().setText(movieItem.getTitle());
        holder.getmTextDescription().setText(movieItem.getImdbID());

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

    public void updatemVideoItems(ArrayList<Movie2> mMovieItems) {
        this.mMovieItems = mMovieItems;
        this.notifyDataSetChanged();
    }
}
