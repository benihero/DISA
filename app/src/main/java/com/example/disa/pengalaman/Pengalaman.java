package com.example.disa.pengalaman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disa.Dashboard;
import com.example.disa.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class Pengalaman extends AppCompatActivity {
    final int VISIBLE = 4;

    Button add_exp,back;
    ArrayList<PengalamanEntity> listt;
    RecyclerView reyy;
    DatabaseReference reference;
    AdapterPengalaman UserAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengalaman);

        reyy = findViewById(R.id.recyclepengalaman);
        back = findViewById(R.id.back_pengalaman);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Pengalaman.this, Dashboard.class);
                startActivity(intent);
                finish();

            }
        });


        reyy.setLayoutManager(new LinearLayoutManager(this));
        listt = new ArrayList<PengalamanEntity>();


        reference = FirebaseDatabase.getInstance().getReference().child("pengalaman");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    PengalamanEntity p = dataSnapshot1.getValue(PengalamanEntity.class);
                    listt.add(p);


                }
                UserAdapter = new AdapterPengalaman(Pengalaman.this, listt);
                reyy.setAdapter(UserAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        add_exp = findViewById(R.id.add_experience);
        add_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pengalaman.this, add_pengalaman.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
