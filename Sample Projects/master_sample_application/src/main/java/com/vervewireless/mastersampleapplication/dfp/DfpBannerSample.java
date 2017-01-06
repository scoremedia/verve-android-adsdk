package com.vervewireless.mastersampleapplication.dfp;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.vervewireless.advert.Category;
import com.vervewireless.advert.mediation.DFPExtras;
import com.vervewireless.advert.mediation.VWDFPCustomEventBanner_Android;
import com.vervewireless.mastersampleapplication.R;
import com.vervewireless.mastersampleapplication.Utils;

/**
 * This class shows how to insert a DFP Banner into your application.
 */
public class DfpBannerSample extends Activity {

	/** The view to show the ad. */
	private PublisherAdView adView;

	//update this value with your DFP Ad Unit ID
	protected String MY_AD_UNIT_ID = "/11027047/vrvanapban";

	//update this value with your Verve Mobile supplied keyword
	private static final String MY_PARTNER_KEYWORD = "adsdkexample";

	/** The main container. */
	private RelativeLayout mainLayout;
	/** Activity title */
	protected int title = R.string.dfp_banner_sample;
	/** Ad view alignment*/
	protected int adAlignment = RelativeLayout.ALIGN_PARENT_BOTTOM;

	protected AdSize adSize;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.banner_sample);

		setTitle(title);

		adView = new PublisherAdView(this);
		adView.setAdUnitId(MY_AD_UNIT_ID);
		if (adSize == null) {
			adSize = Utils.isTablet(this) ? AdSize.LEADERBOARD : AdSize.BANNER;
		}
		adView.setAdSizes(adSize);
		
		// Set the AdListener to listen for standard ad events.
		adView.setAdListener(new LoggingAdListener());
		
		// Lookup your RelativeLayout assuming it's been given the attribute android:id="@+id/mainLayout"
		mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
		
		// Add the adView to it.
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params.addRule(adAlignment);
		mainLayout.addView(adView, params);
	}

	/** Called when the refresh button is clicked. */
	public void refreshAd(View unusedView) {
		
		// Not enough space to show ad ???
		int w = (int) (mainLayout.getMeasuredWidth() / getResources().getDisplayMetrics().density);
		int h = (int) (mainLayout.getMeasuredHeight() / getResources().getDisplayMetrics().density);
		int w_min = adView.getAdSize().getWidth();
		int h_min = adView.getAdSize().getHeight();
		if (w < w_min || h < h_min) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Not enough space to show ad.");
			builder.setMessage("Needs " + w_min + "x" + h_min + " dps, but only has " + w + "x" + h + " dps.\nRetry in landscape mode.");
			builder.setPositiveButton("OK", null);
			builder.show();
			return;
		}
		
		adView.loadAd(buildRequest());
	}

	private PublisherAdRequest buildRequest() {
		Builder b = new PublisherAdRequest.Builder();
		
		DFPExtras dfpExtras = new DFPExtras();
		dfpExtras.setPartnerKeyword(MY_PARTNER_KEYWORD);
		dfpExtras.setCategory(Category.NEWS_AND_INFORMATION);

		b.addCustomEventExtrasBundle(VWDFPCustomEventBanner_Android.class, dfpExtras.toBundle());
		return b.build();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (adView != null) {
			adView.resume();
		}
	}

	@Override
	protected void onPause() {
		if (adView != null) {
			adView.pause();
		}
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}
}
