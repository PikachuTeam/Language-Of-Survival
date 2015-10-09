package com.tateam.frenchsurvivalphrases.fragment;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.tateam.frenchsurvivalphrases.R;
import com.tateam.frenchsurvivalphrases.app.BaseFragment;

import java.util.Locale;


public class FragmentViewdetail extends BaseFragment {

    private TextView tvfrench;
    private CardView btPlay;
    private String InfoDetail;
    private TextToSpeech textToSpeech;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.view_detail, container, false);
        tvfrench= (TextView) v.findViewById(R.id.tvFench);
        btPlay= (CardView) v.findViewById(R.id.buttonSpeak);
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textToSpeech!=null) {
                    textToSpeech.speak(InfoDetail, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
        getData();
        return v;

    }

public void getData(){
    Bundle bundle = this.getArguments();
     InfoDetail = bundle.getString("KEYDETAIL");
     tvfrench.setText(InfoDetail);


}  @Override
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
}
