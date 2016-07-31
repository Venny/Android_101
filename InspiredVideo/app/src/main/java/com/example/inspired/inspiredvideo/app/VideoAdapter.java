package com.example.inspired.inspiredvideo.app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.utils.VideoItem;
import com.example.inspired.inspiredvideo.utils.VideoViewHolder;

import java.util.ArrayList;

/**
 * Created by inspired on 12.07.16.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder>{
    private ArrayList<VideoItem> mVideoItems;
    private OnItemClickListener mOnItemClickListener;

    public VideoAdapter(ArrayList<VideoItem> videoItems,
                        OnItemClickListener onItemClickListener) {
        this.mVideoItems = videoItems;
        this.mOnItemClickListener = onItemClickListener;
    }

    // Creating new view grid items.
    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new VideoViewHolder(vHolder);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, final int position) {
        VideoItem videoItem = mVideoItems.get(position);
        holder.getmImageView().setImageResource(videoItem.getImageRes());
        holder.getmNameView().setText(videoItem.getName());
        holder.getmTextDescription().setText(videoItem.getDescription());

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
        return mVideoItems.size();
    }

    public void setmVideoItems(ArrayList<VideoItem> mVideoItems) {
        this.mVideoItems = mVideoItems;
    }
}
