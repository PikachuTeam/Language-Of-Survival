package com.tateam.languageofsurvival;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;


import com.tateam.languageofsurvival.database.DataSource;


public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_DURATION = 2000;
    private android.os.Handler handler;
    private boolean isDatabaseImported = false;
    private boolean isWaitingInitData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


       // LocalSharedPreferManager.getInstance().init(getApplicationContext());
        DataSource.getInstance().init(getApplicationContext());
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
        startActivity(new Intent(SplashActivity.this, MainActivity.class));

        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


}