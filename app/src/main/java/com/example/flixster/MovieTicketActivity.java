package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class MovieTicketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MovieTicketActivity", "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_ticket);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://www.fandango.com");
    }
}