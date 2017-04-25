package com.vervewireless.mastersampleapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vervewireless.advert.VerveAdSDK;
import com.vervewireless.mastersampleapplication.appnexus.AppNexusBannerSample;
import com.vervewireless.mastersampleapplication.appnexus.AppNexusInterstitialSample;
import com.vervewireless.mastersampleapplication.appnexus.AppNexusRectangleSample;
import com.vervewireless.mastersampleapplication.dfp.DfpBannerSample;
import com.vervewireless.mastersampleapplication.dfp.DfpInterstitialSample;
import com.vervewireless.mastersampleapplication.dfp.DfpRectangleSample;
import com.vervewireless.mastersampleapplication.mopub.MoPubBannerSample;
import com.vervewireless.mastersampleapplication.mopub.MoPubInterstitialSample;
import com.vervewireless.mastersampleapplication.mopub.MoPubRectangleSample;
import com.vervewireless.mastersampleapplication.verve.VerveRectangleSample;
import com.vervewireless.mastersampleapplication.verve.VerveSample;
import com.vervewireless.mastersampleapplication.verve.VerveSplashManagerSample;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getTitle() + " - " + VerveAdSDK.sdkVersion());
        init();
    }

    private void init() {
        ListView samples = (ListView) findViewById(R.id.listSamples);
        List<SampleItem> items = new ArrayList<>();
        // Verve
        items.add(new SampleItem(getString(R.string.verve)));
        items.add(new SampleItem(VerveSample.class, getString(R.string.verve_sdk_samples)));
        items.add(new SampleItem(VerveSplashManagerSample.class, getString(R.string.splash_manager_sample)));
        items.add(new SampleItem(VerveRectangleSample.class, getString(R.string.verve_inline_sample)));
        // Dfp
        items.add(new SampleItem(getString(R.string.dfp)));
        items.add(new SampleItem(DfpBannerSample.class, getString(R.string.dfp_banner_sample)));
        items.add(new SampleItem(DfpRectangleSample.class, getString(R.string.dfp_rectangle_sample)));
        items.add(new SampleItem(DfpInterstitialSample.class, getString(R.string.dfp_interstitial_sample)));
        // MoPub
        items.add(new SampleItem(getString(R.string.mopub)));
        items.add(new SampleItem(MoPubBannerSample.class, getString(R.string.mopub_banner_sample)));
        items.add(new SampleItem(MoPubRectangleSample.class, getString(R.string.mopub_rectangle_sample)));
        items.add(new SampleItem(MoPubInterstitialSample.class, getString(R.string.mopub_interstitial_sample)));
        // AppNexus
        items.add(new SampleItem(getString(R.string.appnexus)));
        items.add(new SampleItem(AppNexusBannerSample.class, getString(R.string.appnexus_banner_sample)));
        items.add(new SampleItem(AppNexusRectangleSample.class, getString(R.string.appnexus_rectangle_sample)));
        items.add(new SampleItem(AppNexusInterstitialSample.class, getString(R.string.appnexus_interstitial_sample)));
        //
        final SamplesAdapter adapter = new SamplesAdapter(items);
        samples.setAdapter(adapter);
        samples.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class target = adapter.getItem(position).getTarget();
                if (target != null) {
                    Intent intent = new Intent(MainActivity.this, target);
                    startActivity(intent);
                }
            }
        });
    }
}
