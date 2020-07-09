package com.example.disa.penjadwalan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disa.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class lihat_jadwal extends AppCompatActivity {

    RecyclerView reyjadwal;
    DatabaseReference reference;
    ArrayList<entityJadwal> jadwal;
    AdapterJadwal jadwaladapter;

    Button tambah;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_jadwal);
        tambah = findViewById(R.id.tambahjadwall);
        getUsernameLocal();

        if(username_key_new.equals("admin")){

            tambah.setVisibility(View.VISIBLE);

        }else {

            tambah.setVisibility(View.GONE);

        }


        reyjadwal = findViewById(R.id.reylihatjadwal);

        reyjadwal.setLayoutManager(new LinearLayoutManager(this));
        jadwal = new ArrayList<entityJadwal>();


        reference = FirebaseDatabase.getInstance().getReference().child("jadwal");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    entityJadwal p = dataSnapshot1.getValue(entityJadwal.class);
                    jadwal.add(p);


                }
                jadwaladapter = new AdapterJadwal(jadwal, lihat_jadwal.this);
                reyjadwal.setAdapter(jadwaladapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    public void Tambahjadwal (View view){

        Intent intent = new Intent(lihat_jadwal.this, com.example.disa.penjadwalan.jadwal.class);
        startActivity(intent);



    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }
}
