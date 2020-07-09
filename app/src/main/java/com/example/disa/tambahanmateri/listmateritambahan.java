package com.example.disa.tambahanmateri;

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
import com.example.disa.materi.ListMateri;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listmateritambahan extends AppCompatActivity {

    RecyclerView reytambah;
    ArrayList<EntityTambahan> tambah;
    AdapterTambahan usertambahan;
    Button tambahan,back;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmateritambahan);
        tambahan = findViewById(R.id.tambamaterilagi);
        back = findViewById(R.id.backtambahan);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(listmateritambahan.this, ListMateri.class);
                startActivity(intent);
                finish();

            }
        });

        getUsernameLocal();

        if(username_key_new.equals("admin")){

            tambahan.setVisibility(View.VISIBLE);

        }else {

            tambahan.setVisibility(View.GONE);

        }

        reytambah = findViewById(R.id.reytambahan);



        reytambah.setLayoutManager(new LinearLayoutManager(this));
       tambah = new ArrayList<EntityTambahan>();

        reference = FirebaseDatabase.getInstance().getReference().child("materi");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    EntityTambahan p = dataSnapshot1.getValue(EntityTambahan.class);
                    tambah.add(p);


                }
                usertambahan = new AdapterTambahan( tambah,listmateritambahan.this);
                reytambah.setAdapter(usertambahan);





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public  void addtambahan(View view){

        Intent intent = new Intent(listmateritambahan.this,tambahanmateri.class);
        startActivity(intent);

    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }

    @Override
    public void onBackPressed() {

    }
}
