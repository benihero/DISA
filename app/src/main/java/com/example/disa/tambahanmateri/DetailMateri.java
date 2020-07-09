package com.example.disa.tambahanmateri;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.disa.R;
import com.squareup.picasso.Picasso;

public class DetailMateri extends AppCompatActivity {

    TextView isi,judul;
    ImageView poto;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_materi);

        isi = findViewById(R.id.isitam);
        judul = findViewById(R.id.materitam);
        poto = findViewById(R.id.pototam);


        Intent intent =  getIntent();
        String judul = intent.getStringExtra("judul");
        String isi = intent.getStringExtra("isi");
        String urlll = intent.getStringExtra("uurl");



        this.judul.setText(judul);
        this.isi.setText(isi);
        Picasso.with(context).load(urlll).centerCrop().fit().into(poto);

    }

}
