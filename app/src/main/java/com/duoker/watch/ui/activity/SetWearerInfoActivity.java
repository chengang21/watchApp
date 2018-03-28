package com.duoker.watch.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.duoker.watch.R;
import com.duoker.watch.ui.base.BaseActivity;

/**
 * Created by cheng on 2017/9/3.
 */
public class SetWearerInfoActivity extends BaseActivity  {

    private WebView myWebView;

    public void finishActivity() {
        finish();
    }


    public void showLoading() {
        showProgress();
    }

 

    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_set_wearer_info);

        init();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @SuppressLint("SimpleDateFormat")
    private void init() {
        myWebView = (WebView) findViewById(R.id.dk_webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //设置WebViewClient
        myWebView.setWebViewClient( new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });


        myWebView.loadUrl("http://139.196.226.71/person.html?userid=1");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (Activity.RESULT_OK == resultCode) {
        }
    }
}
