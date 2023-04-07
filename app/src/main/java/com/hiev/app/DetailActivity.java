package com.hiev.app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView tfoto = findViewById(R.id.foto);
        TextView tJudul = findViewById(R.id.judul);
        TextView tIsi = findViewById(R.id.isi);
        int[] foto = {R.drawable.adolf,R.drawable.sekolah,R.drawable.tokoh,R.drawable.persinggahan,R.drawable.pertanian,R.drawable.pertanian1};
        String[] judul = getResources().getStringArray(R.array.dataJudul);
        String[] isi = getResources().getStringArray(R.array.dataIsi);
        String[] list = {"adolf","sekolah","tokoh","persinggahan","pertanian","pertanian1"};
        Intent intent = getIntent();
        String id = intent.getStringExtra("passid");

        for (int i = 0; i < list.length; i++) {
            if (id.equals(list[i])) {
                tfoto.setImageResource(foto[i]);
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
