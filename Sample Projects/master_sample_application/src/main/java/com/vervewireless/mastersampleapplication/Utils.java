package com.vervewireless.mastersampleapplication;

import android.content.Context;

/**
 * Created by damjanh on 14.9.2016.
 */
public class Utils {

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & android.content.res.Configuration.SCREENLAYOUT_SIZE_MASK)
                >= android.content.res.Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
