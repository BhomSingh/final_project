package com.example.finalProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.androidlabs.R;
import com.example.finalProject.Audio.AudioActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button covid = findViewById(R.id.audioData);
        covid.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, AudioActivity.class);
            startActivity(i);
        });
    }
}