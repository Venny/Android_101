package com.example.inspired.inspiredvideo.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inspired.inspiredvideo.R;

/**
 * Created by inspired on 26.07.16.
 */
public class MovieViewHolder extends RecyclerView.ViewHolder {
    private ImageView mImageView;
    private TextView mNameView;
    private TextView mTextDescription;

    public MovieViewHolder(View itemView) {
        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        mNameView = (TextView) itemView.findViewById(R.id.nameTextView);
        mTextDescription = (TextView) itemView.findViewById(R.id.descTextView);
    }

    public TextView getmTextDescription() {
        return mTextDescription;
    }

    public TextView getmNameView() {
        return mNameView;
    }

    public ImageView getmImageView() {
        return mImageView;
    }
}
