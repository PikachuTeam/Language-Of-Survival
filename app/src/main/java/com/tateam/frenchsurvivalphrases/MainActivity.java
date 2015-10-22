package com.tateam.frenchsurvivalphrases;

import android.os.Bundle;
import android.view.View;

import com.tateam.frenchsurvivalphrases.app.BaseActivity;
import com.tateam.frenchsurvivalphrases.app.BaseFragment;

import com.tateam.frenchsurvivalphrases.database.DataSource;
import com.tateam.frenchsurvivalphrases.fragment.ListTopicFragment;
//import com.tateam.frenchsurvivalphrases.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
