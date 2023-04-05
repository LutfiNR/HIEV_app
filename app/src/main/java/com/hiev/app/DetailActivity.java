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

        TextView judul = findViewById(R.id.judul);
        TextView isi = findViewById(R.id.isi);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
