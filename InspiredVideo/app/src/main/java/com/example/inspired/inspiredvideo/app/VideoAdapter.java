package com.example.inspired.inspiredvideo.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.utils.VideoItem;

import java.util.ArrayList;

/**
 * Created by inspired on 12.07.16.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder>{
    private Context mContext;
    private ArrayList<VideoItem> mVideoItems;

    public VideoAdapter(Context context, ArrayList<VideoItem> videoItems) {
        mContext = context;
        mVideoItems = videoItems;
    }

    // Creating new view grid items.
    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        VideoViewHolder vh = new VideoViewHolder(vHolder);
        return vh;
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        VideoItem video = mVideoItems.get(position);
        holder.getmImageView().setImageResource(video.getImageRes());
        holder.getmNameView().setText(video.getName());
        holder.getmTextDescription().setText(video.getDescription());
    }

    @Override
    public int getItemCount() {
        return mVideoItems.size();
    }
}
