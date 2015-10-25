package com.tateam.languageofsurvival;

import android.os.Bundle;

import com.tateam.languageofsurvival.app.BaseActivity;
import com.tateam.languageofsurvival.app.BaseFragment;
import com.tateam.languageofsurvival.database.DataSource;
import com.tateam.languageofsurvival.fragment.ListTopicFragment;

import tatteam.com.app_common.AppCommon;
//import com.tateam.frenchsurvivalphrases.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCommon.getInstance().initIfNeeded(getApplicationContext());
    }
    @Override
    protected BaseFragment getFragmentContent() {
        return new ListTopicFragment();

    }
    @Override
    protected void onDestroy() {
        DataSource.getInstance().destroy();

        super.onDestroy();
    }
}
