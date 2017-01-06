package com.vervewireless.mastersampleapplication.appnexus;

import android.util.Log;

import com.appnexus.opensdk.AdListener;
import com.appnexus.opensdk.AdView;
import com.appnexus.opensdk.ResultCode;

/**
 * An {@link com.appnexus.opensdk.AdListener} that logs ad events.
 */
class LoggingAdListener implements AdListener {

    /** The log tag. */
    private static final String LOG_TAG = "AppNexusBannerSample";

    @Override
    public void onAdLoaded(AdView adView) {
        Log.d(LOG_TAG, "onAdLoaded");
    }

    @Override
    public void onAdRequestFailed(AdView adView, ResultCode resultCode) {
        String message = String.format("onAdFailedToLoad (%s)", getErrorReason(resultCode));
        Log.d(LOG_TAG, message);
    }

    @Override
    public void onAdExpanded(AdView adView) {
        Log.d(LOG_TAG, "onAdExpanded");
    }

    @Override
    public void onAdCollapsed(AdView adView) {
        Log.d(LOG_TAG, "onAdCollapsed");
    }

    @Override
    public void onAdClicked(AdView adView) {
        Log.d(LOG_TAG, "onAdClicked");
    }

    /** Gets a string error reason from an error code. */
    private String getErrorReason(ResultCode errorCode) {
        String errorReason = "";
        switch(errorCode) {
            case INTERNAL_ERROR:
                errorReason = "Internal error";
                break;
            case INVALID_REQUEST:
                errorReason = "Invalid request";
                break;
            case NETWORK_ERROR:
                errorReason = "Network Error";
                break;
            case UNABLE_TO_FILL:
                errorReason = "No fill";
                break;
        }
        return errorReason;
    }
}
