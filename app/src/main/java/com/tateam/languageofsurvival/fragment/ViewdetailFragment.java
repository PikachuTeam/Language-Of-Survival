package com.tateam.languageofsurvival.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tateam.languageofsurvival.R;
import com.tateam.languageofsurvival.app.BaseFragment;

import tatteam.com.app_common.util.AppSpeaker;

public class ViewdetailFragment extends BaseFragment {

    private TextView tvfrench, tvenglish;
    private FloatingActionButton btPlay;
    private String InfoDetail, StringTrim1,StringTrim2;
    public String[] inforTransfer;
    private String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_detail, container, false);
        tvfrench = (TextView) v.findViewById(R.id.tvFrench);
        tvenglish = (TextView) v.findViewById(R.id.tvEnglish);
        tvfrench.setMovementMethod(new ScrollingMovementMethod());
        tvenglish.setMovementMethod(new ScrollingMovementMethod());

        btPlay = (FloatingActionButton) v.findViewById(R.id.fab_speak);
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        getData();

        return v;

    }

    @Override
    protected void onSpeakPressed() {
        AppSpeaker.getInstance().speak(InfoDetail);
    }

    @Override
    protected boolean enableSpeak() {
        return true;
    }

    public void getData() {
        Bundle bundle = this.getArguments();
        title = bundle.getString("Title");
        inforTransfer = bundle.getStringArray("KEYDETAIL");
        StringTrim1=inforTransfer[0].trim();
        StringTrim2=inforTransfer[1].trim();
        tvenglish.setText(StringTrim1);
        tvfrench.setText(StringTrim2);
        InfoDetail = inforTransfer[1];
    }

    @Override
    public void onStop() {
        super.onStop();
        AppSpeaker.getInstance().stop();
    }

    @Override
    protected String setTitle() {
        return title;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    protected boolean enableBackButton() {
        return true;
    }

    public boolean enablefloatButton() {
        return true;
    }

    public boolean enableTitle() {
        return true;
    }


}
