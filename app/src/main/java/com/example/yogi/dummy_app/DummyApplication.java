package com.example.yogi.dummy_app;

import android.app.Application;
import android.content.Context;

/**
 * Created by YOGI on 24-11-2017.
 */

public class DummyApplication extends Application {
    private static DummyApplication dummyApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        dummyApplication=this;
    }

    public static Context getContext()
    {
        return dummyApplication.getApplicationContext();
    }
}
