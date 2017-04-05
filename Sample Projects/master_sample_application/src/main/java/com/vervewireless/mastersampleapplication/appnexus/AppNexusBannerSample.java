package com.vervewireless.mastersampleapplication.appnexus;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.appnexus.opensdk.BannerAdView;
import com.vervewireless.advert.Category;
import com.vervewireless.advert.mediation.AppNexusExtras;
import com.vervewireless.mastersampleapplication.R;
import com.vervewireless.mastersampleapplication.Utils;


/**
 * This class shows how to insert a AppNexus Banner into your application.
 */
public class AppNexusBannerSample extends Activity{

    //update this value with your Verve Mobile supplied keyword
    private static final String MY_PARTNER_KEYWORD = "adsdkexample";

    //Banner placement ID
    protected String PLACEMENT_ID_PHONE = "11113613";
    protected String PLACEMENT_ID_TABLET = "11113618";
    //Phone banner size.
    protected int adWidth;
    protected int adHeight;

    /** The view to show the ad. */
    private BannerAdView adView;

    /** The main container. */
    private RelativeLayout mainLayout;

    /** Activity title. */
    protected int title = R.string.appnexus_banner_sample;
    /** Ad view alignment*/
    protected int adAlignment = RelativeLayout.ALIGN_PARENT_BOTTOM;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banner_sample);
        setTitle(title);

        adView = new BannerAdView(this);

        //Set ad view params.
        adView.setAutoRefreshInterval(0); //Disable auto refresh
        adView.setShouldReloadOnResume(false);
        adView.setShouldServePSAs(true);
        adView.setOpensNativeBrowser(true);

        adView.setPlacementID(Utils.isTablet(this) ? PLACEMENT_ID_TABLET : PLACEMENT_ID_PHONE);
        if(adHeight !=250 && adWidth != 300) {
            if (Utils.isTablet(this)) {
                //Tablet banner size.
                adWidth = 728;
                adHeight = 90;
            } else {
                adWidth = 320;
                adHeight = 50;
            }
        }
        //Set size to ad view for request.
        adView.setAdSize(adWidth, adHeight);

        // Set the AdListener to listen for standard ad events.
        adView.setAdListener(new LoggingAdListener());

        // Lookup your RelativeLayout assuming it's been given the attribute android:id="@+id/mainLayout"
        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        // Add the adView to it.
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(adAlignment);
        mainLayout.addView(adView, params);
    }

    /** Called when the refresh button is clicked. */
    public void refreshAd(View unusedView) {
        // Not enough space to show ad ???
        int w = (int) (mainLayout.getMeasuredWidth() / getResources().getDisplayMetrics().density);
        int h = (int) (mainLayout.getMeasuredHeight() / getResources().getDisplayMetrics().density);
        int w_min = adView.getAdWidth();
        int h_min = adView.getAdHeight();
        if (w < w_min || h < h_min) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Not enough space to show ad.");
            builder.setMessage("Needs " + w_min + "x" + h_min + " dps, but only has " + w + "x" + h + " dps.\nRetry in landscape mode.");
            builder.setPositiveButton("OK", null);
            builder.show();
            return;
        }

        buildRequest();
    }

    private void buildRequest() {
        AppNexusExtras appNexusExtras = new AppNexusExtras();
        appNexusExtras.setPartnerKeyword(MY_PARTNER_KEYWORD);
        appNexusExtras.setCategory(Category.NEWS_AND_INFORMATION);

        adView = (BannerAdView) appNexusExtras.setParamsToAdView(adView);
        adView.loadAd();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adView != null) {
            adView.activityOnResume();
        }
    }

    @Override
    protected void onPause() {
        if (adView != null) {
            adView.activityOnPause();
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
