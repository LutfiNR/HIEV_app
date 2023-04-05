package com.hiev.app;

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
        TextView textView = findViewById(R.id.teks);
        Button btnAudio = findViewById(R.id.btnAudio);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
