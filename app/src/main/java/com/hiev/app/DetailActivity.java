package com.hiev.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Set up the UI elements for the activity
//        TextView textView = findViewById(R.id.judul);
//        Button btnAudio = findViewById(R.id.isi);
//
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
