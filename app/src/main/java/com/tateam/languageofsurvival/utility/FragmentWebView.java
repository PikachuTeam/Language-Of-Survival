package com.tateam.languageofsurvival.utility;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tateam.languageofsurvival.R;
import com.tateam.languageofsurvival.app.BaseFragment;


public class FragmentWebView extends BaseFragment {

    public WebView mWebView;
    public String inforDetail;

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
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        mWebView = (WebView) view.findViewById(R.id.activity_main_webview);
        Bundle bundle = this.getArguments();
        inforDetail = bundle.getString("inforWeb");
        setUpWebView();
        // mWebView.getSettings().setJavaScriptEnabled(true);
        // mWebView.loadUrl("http://translate.google.com/");
        return view;
    }

    public void setUpWebView() {
        MyWebViewClient myBrowser = new MyWebViewClient();
        mWebView.setWebViewClient(myBrowser);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.loadUrl(getUrl());
    }

    public String getUrl() {
        String url = "https://www.google.com/search?q=";
        if (inforDetail != null) {
            String[] part = inforDetail.split(" ");
            for (int i = 0; i < part.length; i++) {
                url = url + "+" + part[i];
            }
        }
        return url;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}
