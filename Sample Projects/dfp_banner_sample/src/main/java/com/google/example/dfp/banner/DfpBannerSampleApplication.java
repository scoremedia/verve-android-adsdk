package com.google.example.dfp.banner;

import android.app.Application;

import com.vervewireless.advert.VerveAdSDK;

/**
 * Created by zvonkok on 02.02.2016.
 */
public class DfpBannerSampleApplication extends Application {

    private VerveAdSDK verveAdSDK;

    @Override
    public void onCreate() {
        super.onCreate();
        verveAdSDK = new VerveAdSDK(this);
    }
}
