package com.example.disa.video;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.disa.Dashboard;
import com.example.disa.R;



public class Lihat_Video extends AppCompatActivity {


    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        back = findViewById(R.id.back_video);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lihat_Video.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void pindahvideotuli(View view){
        Intent intent = new Intent(Lihat_Video.this,tulivideo.class);
        startActivity(intent);
        finish();
    }
    public void pindahvideoautis(View view){
        Intent intent = new Intent(Lihat_Video.this,autisvideo.class);
        startActivity(intent);
        finish();
    }
    public void pindahvideonetra(View view){
        Intent intent = new Intent(Lihat_Video.this,netravideo.class);
        startActivity(intent);
        finish();
    }
    public void pindahvideodaksa(View view){
        Intent intent = new Intent(Lihat_Video.this,daksavideo.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {

    }
}
