package com.example.disa.soal.soaltuli;

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
import com.example.disa.soal.halamanutamasoal;
import com.example.disa.soal.skorDisplay;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class soaltulidisplay extends AppCompatActivity {

    RecyclerView reysoal;
    DatabaseReference reference;
    ArrayList<EntityTuli> soalll;
    AdapterTuli soaladapter;
    Button selesai,back;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soaltulidisplay);

        reysoal = findViewById(R.id.reysoaltuli);
        selesai = findViewById(R.id.selesaituli);
        back = findViewById(R.id.back_soaltuli);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(soaltulidisplay.this, halamanutamasoal.class);
                startActivity(intent);
                finish();

            }
        });

        reysoal.setLayoutManager(new LinearLayoutManager(this));
        soalll = new ArrayList<EntityTuli>();


        reference = FirebaseDatabase.getInstance().getReference().child("soal").child("Tuli");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    EntityTuli p = dataSnapshot1.getValue(EntityTuli.class);
                    soalll.add(p);


                }
                soaladapter = new AdapterTuli(soaltulidisplay.this, soalll);
                reysoal.setAdapter(soaladapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<EntityTuli> entityTulis = soaladapter.getSoaltuli();
                int score = 0;

                for (EntityTuli item : entityTulis) {

                    if (item.isResult() != false) {

                        score += 1;

                    }


                }

                int nilaifix = ((score *100)/entityTulis.size());
                getUsernameLocal();
                reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("skor").child("skortuli");
                reference.getRef().setValue(nilaifix);


                Intent intent = new Intent(soaltulidisplay.this, skorDisplay.class);
                intent.putExtra("tuli","tuli");
                startActivity(intent);
                finish();


            }
        });

    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }

    @Override
    public void onBackPressed() {

    }






}
