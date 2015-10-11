package com.tateam.frenchsurvivalphrases.utility;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.tateam.frenchsurvivalphrases.R;
import com.tateam.frenchsurvivalphrases.app.BaseFragment;


public class FragmentWebView extends BaseFragment {

    private WebView mWebView;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       // WebSettings webSettings = mWebView.getSettings();
        //webSettings.setJavaScriptEnabled(false);
       // mWebView.loadUrl("http://beta.html5test.com/");
    }

    @Override
    protected boolean enableBackButton() {
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_web_view, container, false);
        mWebView = (WebView) view.findViewById(R.id.activity_main_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://translate.google.com/");
        return view;
    }



}
