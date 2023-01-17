package com.aarafrao.salesmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebActivity extends AppCompatActivity {
    WebView webView;
    String URL;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        URL = getIntent().getStringExtra("url");
        webView = findViewById(R.id.webView);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new Callback());
        webView.loadUrl(URL);

    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}