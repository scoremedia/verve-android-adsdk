package com.vervewireless.mastersampleapplication.mopub;


import android.widget.RelativeLayout;

import com.vervewireless.mastersampleapplication.R;

/**
 * This class shows how to request and display a MoPub Rectangle in your application.
 */

public class MoPubRectangleSample extends MoPubBannerSample {

    public MoPubRectangleSample() {
        title = R.string.mopub_rectangle_sample;
        adAlignment = RelativeLayout.CENTER_IN_PARENT;
        MY_AD_UNIT_ID_PHONE = MY_AD_UNIT_ID_TABLET ="36520db4b19a432faeca8a57760adca6";
    }
}
