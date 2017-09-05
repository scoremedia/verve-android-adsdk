package com.vervewireless.mastersampleapplication;

import android.app.Application;
import android.util.Log;

import com.vervewireless.advert.Category;
import com.vervewireless.advert.LocationPermissionDelegate;
import com.vervewireless.advert.SplashAdManager;
import com.vervewireless.advert.StoragePermissionDelegate;
import com.vervewireless.advert.VWUserDemographics;
import com.vervewireless.advert.VerveAdSDK;
import com.vervewireless.advert.demographics.VWDateComponents;
import com.vervewireless.advert.demographics.VWEducation;
import com.vervewireless.advert.demographics.VWEthnicity;
import com.vervewireless.advert.demographics.VWGender;
import com.vervewireless.advert.demographics.VWMaritalStatus;

public class SampleApplication extends Application {

    private static final String MY_AD_KEYWORD = "adsdkexample";

    @Override
    public void onCreate() {
        super.onCreate();
        initVerveAdSDK();
    }

    private void initVerveAdSDK() {
        VerveAdSDK.initialize(this, MY_AD_KEYWORD, new VerveAdSDK.InitializationListener() {
            @Override
            public void onInitialized(VerveAdSDK verveAdSDK) {
                // you can modify the VerveAdSDK object after the initialization had finished
                // e.g. configure permission delegates or Splash Manager...
                SplashAdManager splashAdManager = SplashAdManager.instance();
                splashAdManager.setAdKeyword(MY_AD_KEYWORD);
                splashAdManager.setCategory(Category.NEWS_AND_INFORMATION);
                // Show provided Splash Image on app startup
                /*
                * Images must be named correctly
                *
                * verve_splash - phone portrait
                * verve_splash_land - phone landscape
                * verve_tablet_splash - tablet portrait
                * verve_tablet_splash_land - tablet landscape
                *
                */
                //splashAdManager.setShowSplashImageOnAppStart(true);
                //splashAdManager.start();

                // Only used when targeting API 23 and up
                verveAdSDK.setLocationPermissionDelegate(new LocationPermissionDelegate() {
                    @Override
                    public boolean shouldAdLibraryRequestLocationPermission() {
                        /*
                        * The library will request user permissions to use location services when the app is brought to foreground.
                        * If you want to manually request permission to use location services or you want to delay it until some
                        * later time, you can cancel library's request.
                        * */
                        return true;
                    }
                });

                // Only used when targeting API 23 and up
                verveAdSDK.setStoragePermissionDelegate(new StoragePermissionDelegate() {
                    @Override
                    public boolean shouldAdLibraryRequestStoragePermission() {
                         /*
                        * The library will request user permissions to use external storage when needed.
                        * If you want to manually request permission to use external storage or you want to delay it until some
                        * later time, you can cancel library's request.
                        * */
                        return true;
                    }
                });

                // Enable logging, possible values Log.INFO (default), Log.WARN, Log.ERROR
                verveAdSDK.setLogPrintLevel(Log.INFO);


                //Set the user demographics data
                VWUserDemographics userDemographics = new VWUserDemographics();

                userDemographics.setBirthDateComponents(new VWDateComponents(1992, 3, 15));
                //Can also use the following two.
//                userDemographics.setAgeRange(VWAgeRange.FROM_18_TO_24);
//                userDemographics.setAge(23);

                userDemographics.setIncome(123541);
                //Can also use the following
//                userDemographics.setIncomeRange(VWIncomeRange.FROM_100K_TO_150K);

                userDemographics.setMaritalStatus(VWMaritalStatus.MARRIED);
                userDemographics.setEducation(VWEducation.DOCTORATE);
                userDemographics.setGender(VWGender.MALE);
                userDemographics.setEthnicity(VWEthnicity.ASIAN);
                userDemographics.setOther("car", "Ford Mustang");

                //Builder pattern is also available.

//                userDemographics = new VWUserDemographicsBuilder().
//                        ageRange(VWAgeRange.FROM_45_TO_54).
//                        education(VWEducation.HIGH_SCHOOL).
//                        ethnicity(VWEthnicity.HISPANIC).
//                        gender(VWGender.MALE).
//                        incomeRange(VWIncomeRange.FROM_50K_TO_75K).
//                        build();

                verveAdSDK.setUserDemographics(getApplicationContext(), userDemographics);
            }
        });

    }
}
