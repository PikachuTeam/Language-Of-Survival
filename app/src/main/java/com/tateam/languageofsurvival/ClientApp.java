package com.tateam.languageofsurvival;

import android.app.Application;

import tatteam.com.app_common.AppCommon;
import tatteam.com.app_common.util.AppSpeaker;

/**
 * Created by VULAN on 10/24/2015.
 */
public class ClientApp extends Application {
   @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onTerminate() {
        AppCommon.getInstance().destroy();
        AppSpeaker.getInstance().destroy();
        super.onTerminate();
    }
}
