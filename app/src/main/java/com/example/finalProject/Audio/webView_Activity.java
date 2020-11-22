package com.example.finalProject.Audio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.androidlabs.R;

public class webView_Activity extends AppCompatActivity {

    Toolbar toolbar;
    private String singer_Name;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_);

        webView = findViewById(R.id.webview);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            singer_Name = getIntent().getExtras().getString("singer_Name").toString();
        }

        String[] splited = singer_Name.split("\\s+");

        String split_one=splited[0];
        String split_second=splited[1];

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("https://www.google.com/search?q="+split_one+"_"+split_second);

    }
}
