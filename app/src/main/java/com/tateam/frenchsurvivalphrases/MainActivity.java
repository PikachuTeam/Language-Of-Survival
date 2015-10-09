package com.tateam.frenchsurvivalphrases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tateam.frenchsurvivalphrases.app.BaseActivity;
import com.tateam.frenchsurvivalphrases.app.BaseFragment;
import com.tateam.frenchsurvivalphrases.fragment.FragmentMain;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BaseFragment getFragmentContent() {
        return new FragmentMain();
    }
}
