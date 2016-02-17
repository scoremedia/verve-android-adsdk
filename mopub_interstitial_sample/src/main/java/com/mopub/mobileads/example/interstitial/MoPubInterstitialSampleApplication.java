package com.mopub.mobileads.example.interstitial;

import android.app.Application;

import com.vervewireless.advert.VerveAdSDK;

/**
 * Created by zvonkok on 02.02.2016.
 */
public class MoPubInterstitialSampleApplication extends Application {

    private VerveAdSDK verveAdSDK;

    @Override
    public void onCreate() {
        super.onCreate();
        verveAdSDK = new VerveAdSDK(this);
    }
}
