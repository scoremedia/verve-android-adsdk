package com.vervewireless.advert.example;

import android.app.Application;

import com.vervewireless.advert.VerveAdSDK;

/**
 * Created by damjanh on 11.1.2016.
 */
public class SampleApplication extends Application {

    private VerveAdSDK verveAdSDK;

    @Override
    public void onCreate() {
        super.onCreate();
        verveAdSDK = new VerveAdSDK(this);
    }
}
