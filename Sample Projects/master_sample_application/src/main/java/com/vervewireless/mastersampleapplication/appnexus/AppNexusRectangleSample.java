package com.vervewireless.mastersampleapplication.appnexus;

import android.widget.RelativeLayout;

import com.vervewireless.mastersampleapplication.R;

/**
 * This class shows how to request and display a AppNexus Rectangle in your application.
 */

public class AppNexusRectangleSample extends AppNexusBannerSample {

    public AppNexusRectangleSample() {
        title = R.string.appnexus_rectangle_sample;
        adAlignment = RelativeLayout.CENTER_IN_PARENT;
        PLACEMENT_ID_PHONE = PLACEMENT_ID_TABLET = "11113615";
        adWidth = 300;
        adHeight = 250;
    }
}
