package com.tateam.languageofsurvival;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.tateam.languageofsurvival.database.DataSource;

import java.util.Locale;

import tatteam.com.app_common.AppCommon;
import tatteam.com.app_common.util.AppSpeaker;

//import java.util.logging.Handler;


public class SplashActivity extends BaseSplashActivity {

    @Override
    protected Locale getLocale() {
        return Locale.FRANCE;
    }
}