package com.vervewireless.mastersampleapplication.mopub;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mopub.mobileads.MoPubView;
import com.vervewireless.advert.Category;
import com.vervewireless.advert.mediation.MoPubExtras;
import com.vervewireless.advert.mediation.VerveExtras;
import com.vervewireless.mastersampleapplication.R;
import com.vervewireless.mastersampleapplication.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * This class shows how to insert a MoPub Banner into your application.
 */
public class MoPubBannerSample extends Activity {

	//Update these values with your MoPub Ad Unit ID
	protected String MY_AD_UNIT_ID_PHONE = "ab71832d98e74c53bcc749b3cfe3b045";	// Ad Unit ID for 320x50 ads
	protected String MY_AD_UNIT_ID_TABLET = "729b85bf451a498085d483b2822850da";	// Ad Unit ID for 728x90 ads

	/** Auto-refresh checkbox state key used for saving and retrieving on orientation changes*/
	private static final String CHECKBOX_STATE = "checkboxState";
	
	/** The view to show the ad. */
	private MoPubView adView;
	
	/** The main container. */
	private RelativeLayout mainLayout;

	/** Activity title */
	protected int title = R.string.mopub_banner_sample;
	/** Ad view alignment*/
	protected int adAlignment = RelativeLayout.ALIGN_PARENT_BOTTOM;

	/** The Checkbox for auto-refresh */
	private CheckBox checkBoxAutoRefresh;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.banner_sample);
		setTitle(title);
		
		adView = new MoPubView(this);
		adView.setAdUnitId(Utils.isTablet(this) ? MY_AD_UNIT_ID_TABLET : MY_AD_UNIT_ID_PHONE);
		
		// Lookup your RelativeLayout assuming it's been given the attribute android:id="@+id/mainLayout"
		mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

		//Init our checkbox for auto refresh
		checkBoxAutoRefresh = new CheckBox(this);
		checkBoxAutoRefresh.setText(getString(R.string.checkBox_autoRefresh));
		if(savedInstanceState != null && savedInstanceState.containsKey(CHECKBOX_STATE)) {
			checkBoxAutoRefresh.setChecked(savedInstanceState.getBoolean(CHECKBOX_STATE, false));
		} else {
			checkBoxAutoRefresh.setChecked(false);
		}

		//Add the checkbox
		LinearLayout controlsView = (LinearLayout) findViewById(R.id.controlsContainer);
		LinearLayout.LayoutParams paramsCb = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		controlsView.addView(checkBoxAutoRefresh, paramsCb);

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
		int w_min = Utils.isTablet(this) ? 728 : 320;
		int h_min = Utils.isTablet(this) ? 90 : 50;
		if (w < w_min || h < h_min) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Not enough space to show ad.");
			builder.setMessage("Needs " + w_min + "x" + h_min + " dps, but only has " + w + "x" + h + " dps.\nRetry in landscape mode.");
			builder.setPositiveButton("OK", null);
			builder.show();
			return;
		}
		
		MoPubExtras mopubExtras = new MoPubExtras();
		mopubExtras.setCategory(Category.NEWS_AND_INFORMATION);

		Map<String, Object> localExtras = new HashMap<>();
		localExtras.put(VerveExtras.EXTRAS_LABEL, mopubExtras);
		
		adView.setLocalExtras(localExtras);
		adView.setAutorefreshEnabled(checkBoxAutoRefresh.isChecked());
		adView.loadAd();
	}

	@Override
	protected void onDestroy() {
		adView.destroy();
		super.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		//Keep checkbox state through orientation state changes
		outState.putBoolean(CHECKBOX_STATE, checkBoxAutoRefresh.isChecked());
	}
}
