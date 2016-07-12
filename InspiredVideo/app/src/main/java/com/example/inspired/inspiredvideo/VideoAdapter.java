package com.example.inspired.inspiredvideo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by inspired on 12.07.16.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{
    private Context mContext;
    private ArrayList<VideoItem> mVideoItems;

    public VideoAdapter(Context context, ArrayList<VideoItem> videoItems) {
        mContext = context;
        mVideoItems = videoItems;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mNameView;
        public TextView mTextDescription;

        public VideoViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mNameView = (TextView) itemView.findViewById(R.id.nameTextView);
            mTextDescription = (TextView) itemView.findViewById(R.id.descTextView);
        }
    }

    // Creating new view grid items.
    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item_layout, parent, false);
        VideoViewHolder vh = new VideoViewHolder(vHolder);
        return vh;
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        VideoItem video = mVideoItems.get(position);
        holder.mImageView.setImageResource(video.getImageRes());
        holder.mNameView.setText(video.getName());
        holder.mTextDescription.setText(video.getDescription());
    }

    @Override
    public int getItemCount() {
        return mVideoItems.size();
    }
}
