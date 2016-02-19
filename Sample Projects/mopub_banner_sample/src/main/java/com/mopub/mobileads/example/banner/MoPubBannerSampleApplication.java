package com.mopub.mobileads.example.banner;

import android.app.Application;

import com.vervewireless.advert.VerveAdSDK;

/**
 * Created by zvonkok on 02.02.2016.
 */
public class MoPubBannerSampleApplication extends Application {

    private VerveAdSDK verveAdSDK;

    @Override
    public void onCreate() {
        super.onCreate();
        verveAdSDK = new VerveAdSDK(this);
    }
}
