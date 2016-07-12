package com.example.inspired.inspiredvideo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by inspired on 12.07.16.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{
    private Context mContext;
    private ArrayList<VideoItem> mVideoItems;

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        public TextView mNameView;
        public TextView mTextDescription;

        public VideoViewHolder(View itemView) {
            super(itemView);
            mNameView = (TextView) itemView.findViewById(R.id.nameTextView);
            mTextDescription = (TextView) itemView.findViewById(R.id.descTextView);
        }
    }

    public VideoAdapter(Context context, ArrayList<VideoItem> videoItems) {
        mContext = context;
        mVideoItems = videoItems;
    }

    // Creating new views.
    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.id.nameTextView, parent, false);
        VideoViewHolder vh = new VideoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mVideoItems.size();
    }
}
