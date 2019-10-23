package com.zonghong.cuntao;

import android.app.Activity;
import android.app.Application;

import com.zonghong.cuntao.listener.MActivtiyLifecycleCallBack;

public class MAPP extends Application {

    public static MAPP mapp;

    private Activity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        mapp = this;
        registerActivityLifecycleCallbacks(new MActivtiyLifecycleCallBack());
    }


    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }
}
