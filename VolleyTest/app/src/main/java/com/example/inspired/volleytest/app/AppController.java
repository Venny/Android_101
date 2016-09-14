package com.example.inspired.volleytest.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NoCache;
import com.android.volley.toolbox.Volley;

/**
 * Created by inspired on 15.07.16.
 */
public class AppController extends Application {
    private RequestQueue mRequestQueue;
    private RequestQueue mNoCacheRequestQueue;
    private ImageLoader mImageLoader;
    private static  AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getmRequestQueue(boolean shouldCache){
        if(!shouldCache){
            return getmRequestQueue();
        } else {
            if(mNoCacheRequestQueue == null) {
                mNoCacheRequestQueue = new RequestQueue(new NoCache(), new BasicNetwork(new HurlStack()));
            }
        }
        return mNoCacheRequestQueue;
    }

    public RequestQueue getmRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }
}
