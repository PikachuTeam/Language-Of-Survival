package com.tateam.languageofsurvival.fragment;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tateam.languageofsurvival.R;
import com.tateam.languageofsurvival.app.BaseActivity;
import com.tateam.languageofsurvival.app.BaseFragment;

import java.util.Locale;

import tatteam.com.app_common.util.AppSpeaker;

//import com.getbase.floatingactionbutton.FloatingActionButton;


public class ViewdetailFragment extends BaseFragment implements
        TextToSpeech.OnInitListener {

    private TextView tvfrench, tvenglish;
    private FloatingActionButton btPlay;
    private String InfoDetail;
    public String[] inforTransfer;
    private TextToSpeech textToSpeech;
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
                if (textToSpeech != null) {
                    textToSpeech.speak(inforTransfer[1], TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
        getData();
//        getBaseActivity().getToolbar().findViewById(R.id.action_speak).setVisibility(View.VISIBLE);
//        getBaseActivity().getToolbar().findViewById(R.id.action_speak).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppSpeaker.getInstance().speak(InfoDetail);
//            }
//        });

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
        title=bundle.getString("Title");
        inforTransfer = bundle.getStringArray("KEYDETAIL");
        tvenglish.setText(inforTransfer[0]);
        tvfrench.setText(inforTransfer[1]);
        InfoDetail = inforTransfer[1];
    }

    @Override
    protected void onBackPressed() {
        AppSpeaker.getInstance().stop();
        super.onBackPressed();
    }

    @Override
    protected String setTitle() {
        return title;
    }

    @Override
    public void onResume() {
        super.onResume();
        textToSpeech = new TextToSpeech(getBaseActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.FRANCE);
                    textToSpeech.setSpeechRate(0.8f);
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
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

    @Override
    public void onInit(int status) {
        /*
        if(status==TextToSpeech.ERROR){
            getBaseActivity().getToolbar().findViewById(R.id.action_speak).setVisibility(View.INVISIBLE);

        }
        */
        int result = textToSpeech.setLanguage(new Locale("sp"));

        if (result == TextToSpeech.LANG_MISSING_DATA
                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            getBaseActivity().getToolbar().findViewById(R.id.action_speak).setVisibility(View.INVISIBLE);

        }

    }

}
