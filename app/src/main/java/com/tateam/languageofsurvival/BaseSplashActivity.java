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

/**
 * Created by ThanhNH-Mac on 10/29/15.
 */
public abstract  class BaseSplashActivity extends AppCompatActivity {
    private static final long SPLASH_DURATION = 2000;
    private android.os.Handler handler;
    private boolean isDatabaseImported = false;
    private boolean isWaitingInitData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initAppCommon();
        initAppSpeaker();
        // LocalSharedPreferManager.getInstance().initIfNeeded(getApplicationContext());
        DataSource.getInstance().initIfNeeded(getApplicationContext());
        importDatabase();

        handler = new android.os.Handler(new android.os.Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (isDatabaseImported) {
                    isDatabaseImported = false;
                    switchToMainActivity();
                } else {
                    isWaitingInitData = true;
                }
                return false;
            }
        });
        handler.sendEmptyMessageDelayed(0, SPLASH_DURATION);

    }

    private void initAppCommon() {
        AppCommon.getInstance().initIfNeeded(getApplicationContext());
        AppCommon.getInstance().increaseLaunchTime();
    }

    private void initAppSpeaker() {
        AppSpeaker.getInstance().initIfNeeded(getApplicationContext(), getLocale());
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    private void importDatabase() {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                DataSource.getInstance().createDatabaseIfNeed();
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                isDatabaseImported = true;
                if (isWaitingInitData) {
                    switchToMainActivity();
                }
            }
        };
        task.execute();
    }

    private void switchToMainActivity() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(BaseSplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    protected abstract Locale getLocale();


}
