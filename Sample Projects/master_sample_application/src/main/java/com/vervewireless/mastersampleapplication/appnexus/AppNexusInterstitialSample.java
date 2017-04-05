package com.vervewireless.mastersampleapplication.appnexus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appnexus.opensdk.AdListener;
import com.appnexus.opensdk.AdView;
import com.appnexus.opensdk.InterstitialAdView;
import com.appnexus.opensdk.ResultCode;
import com.vervewireless.advert.Category;
import com.vervewireless.advert.mediation.AppNexusExtras;
import com.vervewireless.mastersampleapplication.R;


/**
 * Created by damjanh on 14.9.2016.
 * This class shows how to request and display a AppNexus Interstitials in your application.
 */
public class AppNexusInterstitialSample extends Activity {

    /** The log tag. */
    private static final String LOG_TAG = "InterstitialSample";

    private static final String PLACEMENT_ID = "11113620";

    //update this value with your Verve Mobile supplied keyword
    private static final String MY_PARTNER_KEYWORD = "adsdkexample";

    /** The interstitial ad. */
    private InterstitialAdView interstitialAd;

    /** The "Show Interstitial" button. */
    private Button showButton;
    /** The "Load Interstitial" button. */
    private Button loadButton;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interstitial_sample);

        setTitle(R.string.appnexus_interstitial_sample);

        // Create an ad.
        interstitialAd = new InterstitialAdView(this);
        interstitialAd.setPlacementID(PLACEMENT_ID);

        // Set the AdListener.
        interstitialAd.setAdListener(new InterstitialAdListener());

        showButton = (Button) findViewById(R.id.showButton);
        showButton.setText(R.string.ad_not_ready);
        showButton.setEnabled(false);

        loadButton = (Button) findViewById(R.id.loadButton);
    }

    /** Called when the "Load Interstitial" button is clicked. */
    public void loadInterstitial(View unusedView) {
        showButton.setEnabled(false);
        // Disable the show button until the new ad is loaded.
        showButton.setText(R.string.ad_loading);
        showButton.setEnabled(false);

        // Load the interstitial ad.
        buildRequest();
    }

    private void buildRequest() {
        AppNexusExtras appNexusExtras = new AppNexusExtras();
        appNexusExtras.setPartnerKeyword(MY_PARTNER_KEYWORD);
        appNexusExtras.setCategory(Category.NEWS_AND_INFORMATION);

        interstitialAd = (InterstitialAdView) appNexusExtras.setParamsToAdView(interstitialAd);
        interstitialAd.loadAd();
    }

    public void showInterstitial(View unusedView) {
        // Show the interstitial if it's loaded.
        if (interstitialAd.isReady()) {
            interstitialAd.show();
        } else {
            Log.d(LOG_TAG, "Interstitial ad was not ready to be shown.");
        }

        // Disable the show button until another interstitial is loaded.
        showButton.setText(R.string.ad_not_ready);
        showButton.setEnabled(false);
    }

    /**
     * An ad listener that logs and toasts ad events, and enables/disables the "Show Interstitial"
     * button when appropriate.
     */
    private class InterstitialAdListener implements AdListener {
        /** Called when an ad is loaded. */
        @Override
        public void onAdLoaded(AdView adView) {
            Log.d(AppNexusInterstitialSample.LOG_TAG, "onAdLoaded");
            Toast.makeText(AppNexusInterstitialSample.this, "onAdLoaded", Toast.LENGTH_SHORT).show();

            // Change the button text and enable the show button.
            showButton.setText(R.string.show_interstitial);
            showButton.setEnabled(true);
            loadButton.setEnabled(false);
        }

        /** Called when an ad failed to load. */
        @Override
        public void onAdRequestFailed(AdView adView, ResultCode resultCode) {
            String message = String.format("onAdFailedToLoad (%s)", getErrorReason(resultCode));
            Log.d(AppNexusInterstitialSample.LOG_TAG, message);
            Toast.makeText(AppNexusInterstitialSample.this, message, Toast.LENGTH_SHORT).show();

            // Change the button text and disable the show button.
            showButton.setText(R.string.ad_failed);
            showButton.setEnabled(false);
            loadButton.setEnabled(true);
        }

        /**
         * Called when an Activity is created in front of the app (e.g. an interstitial is shown, or an
         * ad is clicked and launches a new Activity).
         */
        @Override
        public void onAdExpanded(AdView adView) {
            Log.d(AppNexusInterstitialSample.LOG_TAG, "onAdExpanded");
            Toast.makeText(AppNexusInterstitialSample.this, "onAdExpanded", Toast.LENGTH_SHORT).show();
        }

        /** Called when an ad is closed and about to return to the application. */
        @Override
        public void onAdCollapsed(AdView adView) {
            Log.d(AppNexusInterstitialSample.LOG_TAG, "onAdCollapsed");
            Toast.makeText(AppNexusInterstitialSample.this, "onAdCollapsed", Toast.LENGTH_SHORT).show();
            loadButton.setEnabled(true);
        }

        /**
         * Called when an ad is clicked and going to start a new Activity that will leave the
         * application (e.g. breaking out to the Browser or Maps application).
         */
        @Override
        public void onAdClicked(AdView adView) {
            Log.d(AppNexusInterstitialSample.LOG_TAG, "onAdClicked");
            Toast.makeText(AppNexusInterstitialSample.this, "onAdClicked", Toast.LENGTH_SHORT).show();
        }

        /** Gets a string error reason from an error code. */
        private String getErrorReason(ResultCode errorCode) {
            switch(errorCode) {
                case INTERNAL_ERROR:
                    return "Internal error";
                case INVALID_REQUEST:
                    return "Invalid request";
                case NETWORK_ERROR:
                    return "Network Error";
                case UNABLE_TO_FILL:
                    return "No fill";
                default:
                    return "Unknown error";
            }
        }
    }
}
