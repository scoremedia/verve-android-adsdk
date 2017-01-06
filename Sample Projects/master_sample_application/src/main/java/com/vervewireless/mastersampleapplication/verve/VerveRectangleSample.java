package com.vervewireless.mastersampleapplication.verve;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.vervewireless.advert.Ad;
import com.vervewireless.advert.AdClickedListener;
import com.vervewireless.advert.AdError;
import com.vervewireless.advert.AdListener;
import com.vervewireless.advert.AdResponse;
import com.vervewireless.advert.Category;
import com.vervewireless.advert.VerveWebView;
import com.vervewireless.mastersampleapplication.R;

/**
 * Created by damjanh on 16.5.2016.
 */
public class VerveRectangleSample extends Activity {

    private static final String TAG = VerveRectangleSample.class.getSimpleName();

    private static final String MY_AD_KEYWORD = "adsdkexample";

    private static final String MIME_TYPE = "text/html";
    private static final String ENCODING = "utf-8";
    public static final String domain = "test.test.com";

    private VerveWebView verveWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verve_inline_sample);

        setTitle(R.string.verve_inline_sample);


        verveWebView = (VerveWebView) findViewById(R.id.verveWebView);
        /*
         * You can decide not to have tracking on your users (optional).
		 */
        verveWebView.setLimitUserTrackingEnabled(true);
         /*
         * Add a category to the ad request (optional).
		 * Default to Category.NEWS
		 */
        verveWebView.setAdCategory(Category.HOME_PAGE);
        /*
        * Set the Ad keyword provided by Verve.
        */
        verveWebView.setAdKeyword(MY_AD_KEYWORD);
        /*
        * Set the position of the ad view in the WebView in percentage.
        */
        verveWebView.setAdViewLocationPercentage(60);
        /*
        * Should the AdView animate expand.
        */
        verveWebView.setDoAnimate(true);
        /*
        *  Should the AdView wait for the user to scroll to it before loading.
        */
        verveWebView.setWaitForUserViewPort(true);

        verveWebView.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded(AdResponse response) {
                Log.d(TAG, "AdView loaded");
            }

            @Override
            public void onAdError(AdError e) {
                Log.e(TAG, "AdView error:" + e);
            }

            @Override
            public void onNoAdReturned(AdResponse response) {
                Log.d(TAG, "AdView no ad");
            }

            @Override
            public void onAdPageFinished() {
                Log.d(TAG, "AdView page finished loading");
            }
        });

        verveWebView.setAdClickedListener(new AdClickedListener() {

            @Override
            public boolean onAdClicked(Ad ad, Uri uri) {
                Log.d(TAG, "AdView link clicked - uri: " + uri.toString());

				/*
                 * If you want to handle clicks on ad
				 * and override the default behavior,
				 * return true, otherwise return false.
				 */
                return false;
            }
        });


         /*
        * Load the content of the WebView.
        */
        verveWebView.loadDataWithBaseURL(domain, htmlTest, MIME_TYPE, ENCODING, null);

    }


    private static final String htmlTest = "<html><head>\n" +
            "\t<title></title>\n" +
            "</head>\n" +
            "<body>\n" +
            "\t<div>\n" +
            "\t\t\n" +
            "\t\t<h1>Wrestling | Ohio State's Kyle Snyder earns spot in Olympics</h1>\n" +
            "\t\t<div>\n" +
            "\t\t\tApr 11, 2016 5:43 AM\n" +
            "\t\t</div>\n" +
            "\t\t\n" +
            "\t\t<p>IOWA CITY, Iowa — Ohio State's Kyle Snyder, the youngest wrestling world champion in U.S. history, qualified for his first Olympics at 97 kilograms on Sunday in the Olympic Team Trials.</p>\n" +
            "<p>Snyder, 20, beat Jake Varner, the 2012 London Games gold medalist, 4-4, 4-0 and 6-1 in a best-of-three finals series.</p>\n" +
            "<p>In September, Snyder, then 19, upset defending world champion Abdusalam Gadisov of Russia in the 213-pound weight class of the world championships.</p>\n" +
            "<p>In March, he ended the 88-match winning streak of two-time collegiate national champion Nick Gwiazdowski by rallying late to win the NCAA championship at 285 pounds.</p>\n" +
            "<p><strong><a href=\"http://buckeyextra.dispatch.com/content/stories/2016/04/10/0410-will-smith-shot-to-death.html\" target=\"_blank\">&gt;&gt; Former Ohio State defensive end Will Smith shot to death in New Orleans</a></strong></p>\n" +
            "<p>Snyder's stunning run to a world title last summer in Las Vegas served notice that he might become America's next major wrestling star. Snyder only added to his growing legend by winning the NCAA heavyweight title despite giving up about 40 pounds to Gwiazdowski.</p>\n" +
            "<p>Sunday night, Varner, 30, won the first match on criteria after posting two takedowns. But the younger Snyder wore Varner down over the final two matches, outscoring Varner 10-1 in their final two matches.</p>\n" +
            "<p>\"I watched the 2012 Olympics and I was like `Shoot. If I want to make the Olympics I have to be that guy,' \" said Snyder, then just 16. \"When you've got somebody like that in your class you have to train as hard as you possibly can.\"</p>\n" +
            "<p>Ohio State's Nathan Tomasello scored a last-second takedown for a 4-3 victory over Nico Megaludis in a 57-kilo prelim on Sunday. Tomasello lost in the quarterfinals.</p>\n" +
            "<p>Top-ranked American wrestler Jordan Burroughs secured his spot in the Rio Games. Burroughs, who has three world titles along with an Olympic gold medal from 2012, beat Andrew Howe 9-3 and 10-0. Women's 75-kilogram freestyle star Adeline Gray, a three-time world champion, is also headed to Rio after beating Victoria Francis 11-0 and 10-0.</p>\n" +
            "<p>Daniel Dennis (57 kilograms, freestyle), Andy Bisek (75 kilograms, Greco-Roman) and Robby Smith (heavyweight, Greco-Roman) clinched Olympic bids as well on Sunday.</p>\n" +
            "<p>J'Den Cox (86 kilograms, freestyle), Haley Augello (48 kilograms, women's freestyle) and Helen Maroulis (53 kilograms, freestyle) took first in classes the U.S. has yet to qualify to compete in at the Olympics. All three will try to earn bids for themselves and their country in a pair of last-chance events in Mongolia and Turkey in the next few weeks.</p>\n" +
            "</body></html>";

}


