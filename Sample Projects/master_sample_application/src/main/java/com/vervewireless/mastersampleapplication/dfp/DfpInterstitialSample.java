package com.vervewireless.mastersampleapplication.dfp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.vervewireless.advert.Category;
import com.vervewireless.advert.mediation.DFPExtras;
import com.vervewireless.advert.mediation.VWDFPCustomEventInterstitial_Android;
import com.vervewireless.mastersampleapplication.R;

/**
 * This class shows how to request and display a DFP Interstitials in your application.
 */
public class DfpInterstitialSample extends Activity {

	/** The log tag. */
	private static final String LOG_TAG = "DfpInterstitialSample";

	//update this value with your DFP Ad Unit ID
	protected static final String MY_AD_UNIT_ID = "/11027047/vrvanapint";

	//update this value with your Verve Mobile supplied keyword
	private static final String MY_PARTNER_KEYWORD = "adsdkexample";

	/** The interstitial ad. */
	private PublisherInterstitialAd interstitialAd;

	/** The "Show Interstitial" button. */
	private Button showButton;
	/** The "Load Interstitial" button. */
	private Button loadButton;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.interstitial_sample);
		setTitle(R.string.dfp_interstitial_sample);

		// Create an ad.
		interstitialAd = new PublisherInterstitialAd(this);
		interstitialAd.setAdUnitId(MY_AD_UNIT_ID);
		
		// Set the AdListener.
		interstitialAd.setAdListener(new InterstitialAdListener());
		
		showButton = (Button) findViewById(R.id.showButton);
		showButton.setText(R.string.ad_not_ready);
		showButton.setEnabled(false);

		loadButton = (Button) findViewById(R.id.loadButton);
	}

	/** Called when the "Load Interstitial" button is clicked. */
	public void loadInterstitial(View unusedview) {
		showButton.setEnabled(false);
		// Disable the show button until the new ad is loaded.
		showButton.setText(R.string.ad_loading);
		showButton.setEnabled(false);
		
		// Load the interstitial ad.
		interstitialAd.loadAd(buildRequest());
	}

	private PublisherAdRequest buildRequest() {
		Builder b = new PublisherAdRequest.Builder();
		
		DFPExtras dfpExtras = new DFPExtras();
		dfpExtras.setPartnerKeyword(MY_PARTNER_KEYWORD);
		dfpExtras.setCategory(Category.NEWS_AND_INFORMATION);

		b.addCustomEventExtrasBundle(VWDFPCustomEventInterstitial_Android.class, dfpExtras.toBundle());
		return b.build();
	}

	/** Called when the "Show Interstitial" button is clicked. */
	public void showInterstitial(View unusedView) {
		// Show the interstitial if it's loaded.
		if (interstitialAd.isLoaded()) {
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
	private class InterstitialAdListener extends AdListener {
		/** Called when an ad is loaded. */
		@Override
		public void onAdLoaded() {
			Log.d(DfpInterstitialSample.LOG_TAG, "onAdLoaded");
			Toast.makeText(DfpInterstitialSample.this, "onAdLoaded", Toast.LENGTH_SHORT).show();
			
			// Change the button text and enable the show button.
			showButton.setText(R.string.show_interstitial);
			showButton.setEnabled(true);
			loadButton.setEnabled(false);
		}
		
		/** Called when an ad failed to load. */
		@Override
		public void onAdFailedToLoad(int errorCode) {
			String message = String.format("onAdFailedToLoad (%s)", getErrorReason(errorCode));
			Log.d(DfpInterstitialSample.LOG_TAG, message);
			Toast.makeText(DfpInterstitialSample.this, message, Toast.LENGTH_SHORT).show();
			
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
		public void onAdOpened() {
			Log.d(DfpInterstitialSample.LOG_TAG, "onAdOpened");
			Toast.makeText(DfpInterstitialSample.this, "onAdOpened", Toast.LENGTH_SHORT).show();
		}
		
		/** Called when an ad is closed and about to return to the application. */
		@Override
		public void onAdClosed() {
			Log.d(DfpInterstitialSample.LOG_TAG, "onAdClosed");
			Toast.makeText(DfpInterstitialSample.this, "onAdClosed", Toast.LENGTH_SHORT).show();
			loadButton.setEnabled(true);
		}
		
		/**
		 * Called when an ad is clicked and going to start a new Activity that will leave the
		 * application (e.g. breaking out to the Browser or Maps application).
		 */
		@Override
		public void onAdLeftApplication() {
			Log.d(DfpInterstitialSample.LOG_TAG, "onAdLeftApplication");
			Toast.makeText(DfpInterstitialSample.this, "onAdLeftApplication", Toast.LENGTH_SHORT).show();
		}
		
		/** Gets a string error reason from an error code. */
		private String getErrorReason(int errorCode) {
			switch(errorCode) {
			case AdRequest.ERROR_CODE_INTERNAL_ERROR:
				return "Internal error";
			case AdRequest.ERROR_CODE_INVALID_REQUEST:
				return "Invalid request";
			case AdRequest.ERROR_CODE_NETWORK_ERROR:
				return "Network Error";
			case AdRequest.ERROR_CODE_NO_FILL:
				return "No fill";
			default:
				return "Unknown error";
			}
		}
	}
}
