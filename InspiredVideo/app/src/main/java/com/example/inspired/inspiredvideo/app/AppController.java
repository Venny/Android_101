package com.example.inspired.inspiredvideo.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.NoCache;
import com.android.volley.toolbox.Volley;

/**
 * Created by inspired on 25.07.16.
 */
public class AppController extends Application {
    private RequestQueue mRequestQueue;
    private RequestQueue mNoCacheRequestQueue;
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getmInstance(){
        return mInstance;
    }

    public RequestQueue getmRequestQueue(boolean noCache){
        if(!noCache){
            return getmRequestQueue();
        } else {
           if(mNoCacheRequestQueue == null){
               mNoCacheRequestQueue = new RequestQueue(new NoCache(), new BasicNetwork(new HurlStack()));
           }
            return mNoCacheRequestQueue;
        }
    }

    private RequestQueue getmRequestQueue() {
        if(mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }
}
