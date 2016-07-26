package com.example.inspired.inspiredvideo.utils;

import android.widget.ImageView;

/**
 * Created by inspired on 12.07.16.
 */
public class VideoItem {
    private String name;
    private String description;
    private int imageRes;

    public VideoItem(String name, String description, int imageRes) {
        this.name = name;
        this.description = description;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageRes() {
        return imageRes;
    }
}
