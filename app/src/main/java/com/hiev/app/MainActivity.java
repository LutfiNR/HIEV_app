package com.hiev.app;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = findViewById(R.id.start);
        MediaPlayer welcome = MediaPlayer.create(this,R.raw.welcome);
        welcome.start();
        start.setOnClickListener(arview -> {
            Intent intent = new Intent(MainActivity.this, ArActivity.class);
            startActivity(intent);
        });
    }

}