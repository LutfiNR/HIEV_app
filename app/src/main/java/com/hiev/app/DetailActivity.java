package com.hiev.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tJudul = findViewById(R.id.judul);
        TextView tIsi = findViewById(R.id.isi);
        String[] judul = getResources().getStringArray(R.array.dataJudul);
        String[] isi = getResources().getStringArray(R.array.dataIsi);
        String[] list = {"adolf","sekolah","tokoh","persinggahan","pertanian"};

        Intent intent = getIntent();
        String id = intent.getStringExtra("passid");

        for (int i = 0; i < list.length; i++) {
            if (id.equals(list[i])) {
                tJudul.setText(judul[i]);
                tIsi.setText(isi[i]);
                break;
            }
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
