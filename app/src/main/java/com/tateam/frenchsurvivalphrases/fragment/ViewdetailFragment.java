package com.tateam.frenchsurvivalphrases.fragment;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


//import com.getbase.floatingactionbutton.FloatingActionButton;
import com.tateam.frenchsurvivalphrases.R;
import com.tateam.frenchsurvivalphrases.app.BaseFragment;
import com.tateam.frenchsurvivalphrases.utility.FragmentWebView;

import java.util.Locale;


public class ViewdetailFragment extends BaseFragment {

    private TextView tvfrench,tvenglish;
    private FloatingActionButton btPlay;
    private String InfoDetail;
    public String[] inforTransfer;
    private TextToSpeech textToSpeech;
    private FloatingActionButton floatingActionButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.view_detail, container, false);
        tvfrench= (TextView) v.findViewById(R.id.tvFrench);
        tvenglish= (TextView) v.findViewById(R.id.tvEnglish);
        btPlay= (FloatingActionButton) v.findViewById(R.id.fab_speak);
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textToSpeech!=null) {
                    textToSpeech.speak(inforTransfer[1], TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
        getData();
        floatingActionButton= (FloatingActionButton) v.findViewById(R.id.fabGoogle);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new FragmentWebView());
            }
        });

        return v;

    }

public void getData(){
    Bundle bundle = this.getArguments();
     //InfoDetail = bundle.getString("KEYDETAIL");
    inforTransfer=bundle.getStringArray("KEYDETAIL");
    tvenglish.setText(inforTransfer[0]);
     tvfrench.setText(inforTransfer[1]);


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
    public boolean enablefloatButton() {
        return false;
    }
}
