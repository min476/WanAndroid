package com.vera.sample.wanandroid.app;


import android.app.Application;
import android.content.Context;


public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        initData();
    }

    private void initData() {


    }

    public static Context getContext() {
        return mContext;
    }
}
