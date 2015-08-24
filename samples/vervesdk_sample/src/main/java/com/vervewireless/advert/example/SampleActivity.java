package com.vervewireless.advert.example;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.vervewireless.advert.Ad;
import com.vervewireless.advert.AdClickedListener;
import com.vervewireless.advert.AdError;
import com.vervewireless.advert.AdListener;
import com.vervewireless.advert.AdRequest;
import com.vervewireless.advert.AdResponse;
import com.vervewireless.advert.AdView;
import com.vervewireless.advert.Category;
import com.vervewireless.advert.InterstitialAd;
import com.vervewireless.advert.InterstitialAdListener;

public class SampleActivity extends Activity {

    private static final String MY_AD_KEYWORD = "adsdkexample";
    private static final String TAG = SampleActivity.class.getSimpleName();

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private ToggleButton trackingButton;
    private Toast toast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //WebView.setWebContentsDebuggingEnabled(true);

        trackingButton = (ToggleButton) findViewById(R.id.toggleButton);

        mAdView = (AdView) findViewById(R.id.adView);
        mAdView.setAdClickedListener(new AdClickedListener() {

            @Override
            public boolean onAdClicked(Ad ad, Uri uri) {
                Log.d("Test", "AdView link clicked - uri: " + uri.toString());

				/*
                 * If you want to handle clicks on ad
				 * and override the default behavior,
				 * return true, otherwise return false.
				 */
                return false;
            }
        });
        mAdView.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded(AdResponse response) {
                Log.d("Test", "AdView loaded");
            }

            @Override
            public void onAdError(AdError e) {
                Log.e("Test", "AdView error:" + e);
            }

            @Override
            public void onNoAdReturned(AdResponse response) {
                Log.d("Test", "AdView no ad");
            }

            @Override
            public void onAdPageFinished() {
                Log.d("Test", "AdView page finished loading");
            }
        });

        mInterstitialAd = new InterstitialAd(SampleActivity.this);
        mInterstitialAd.setAdKeyword(MY_AD_KEYWORD);
        mInterstitialAd.setInterstitialAdListener(new InterstitialAdListener() {

            @Override
            public void onAdReady() {
                Log.d("Test", "InterstitialAd downloaded and ready for display");

                mInterstitialAd.display();
            }

            @Override
            public void onAdFailed() {
                Log.d("Test", "InterstitialAd failed");
            }
        });

        Button buttonRefresh = (Button) findViewById(R.id.button1);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestNewAd();
            }
        });

        Button buttonInterstitialAd = (Button) findViewById(R.id.button2);
        buttonInterstitialAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestNewInterstitialAd();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdView.onResume();
        requestNewAd();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAdView.onPause();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void requestNewAd() {
        // Create a new AdRequest.
        AdRequest adRequest = new AdRequest();

		/*
         * Add a category to the ad request (optional).
		 * Default to Category.NEWS
		 */
        adRequest.setCategory(Category.HOME_PAGE);

		/*
         * You can decide not to have tracking on your users (optional).
		 */
        adRequest.setLimitUserTrackingEnabled(!trackingButton.isChecked());

		/*
         * Make the ad request.
		 * Location data is automatically added to the request if
		 * uses-permissions for location set with either:
		 * ACCESS_COARSE_LOCATION
		 * ACCESS_FINE_LOCATION
		 */
        mAdView.requestAd(adRequest);
    }

    private void requestNewInterstitialAd() {
        // Create a new AdRequest.
        AdRequest adRequest = new AdRequest();

		/*
         * Add a category to the ad request (optional).
		 * Default to Category.NEWS
		 */
        adRequest.setCategory(Category.HOME_PAGE);

		/*
         * You can decide not to have tracking on your users (optional).
		 */
        adRequest.setLimitUserTrackingEnabled(!trackingButton.isChecked());

		/*
         * Make the ad request.
		 * Location data is automatically added to the request if
		 * uses-permissions for location set with either:
		 * ACCESS_COARSE_LOCATION
		 * ACCESS_FINE_LOCATION
		 */
        mInterstitialAd.requestAd(adRequest);
    }

    private void showToast(String str) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
        toast.show();
    }



}