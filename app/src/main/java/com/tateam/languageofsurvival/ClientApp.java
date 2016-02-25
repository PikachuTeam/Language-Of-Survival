package com.tateam.languageofsurvival;

import android.app.Application;

import tatteam.com.app_common.AppCommon;
import tatteam.com.app_common.sqlite.DatabaseLoader;
import tatteam.com.app_common.util.AppSpeaker;

/**
 * Created by VULAN on 10/24/2015.
 */
public class ClientApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppCommon.getInstance().initIfNeeded(getApplicationContext());
        DatabaseLoader.getInstance().restoreState(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        AppCommon.getInstance().destroy();
        AppSpeaker.getInstance().destroy();
        DatabaseLoader.getInstance().destroy();
        super.onTerminate();
    }
}
